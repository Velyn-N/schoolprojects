#include <ESP8266WiFi.h>
#include <Ticker.h>
#include <AsyncMqttClient.h>
#include "EspPins.h"
#include "Sensor.h"
#include "Actor.h"
#include "DHT11Sensor.h"

#define WIFI_SSID "Never-gonna-give-you-WiFi"
#define WIFI_PASSWORD "Muemmler"

#define MQTT_HOST IPAddress(10, 0, 0, 1)
#define MQTT_PORT 1883

#define DEVICE_NAME "ESP1"

AsyncMqttClient mqttClient;
Ticker mqttReconnectTimer;

WiFiEventHandler wifiConnectHandler;
WiFiEventHandler wifiDisconnectHandler;
Ticker wifiReconnectTimer;

Sensor* analogSensor;
DHT11Sensor* dht11;
Actor* led;
Actor* led2;

void setup() {
  Serial.begin(115200);
  delay(500);

  wifiConnectHandler = WiFi.onStationModeGotIP(onWifiConnect);
  wifiDisconnectHandler = WiFi.onStationModeDisconnected(onWifiDisconnect);

  mqttClient.onConnect(onMqttConnect);
  mqttClient.onDisconnect(onMqttDisconnect);
  //mqttClient.onSubscribe(onMqttSubscribe);
  //mqttClient.onUnsubscribe(onMqttUnsubscribe);
  //mqttClient.onMessage(onMqttMessage);
  //mqttClient.onPublish(onMqttPublish);
  mqttClient.setServer(MQTT_HOST, MQTT_PORT);
  
  mqttClient.onConnect(handleSubscriptions);
  mqttClient.onMessage(handleActors);



  analogSensor = new Sensor(&mqttClient, "lightswitch", DEVICE_NAME, D2);
  dht11 = new DHT11Sensor(&mqttClient, "temperature", "humidity", DEVICE_NAME, D3);
  led = new Actor("light", DEVICE_NAME, D8, true);
  led2 = new Actor("led2", DEVICE_NAME, D0, false);
  
  connectToWifi();
}

void loop() {
  delay(5000);
  if (!mqttClient.connected()) {
    Serial.println("Not looping. Mqtt not connected!");
    return;
  }
  analogSensor->loopSensor();
  dht11->loopSensor();
}

void handleSubscriptions(bool sessionPresent) {
  led->subscribeToMqtt(&mqttClient);
  led2->subscribeToMqtt(&mqttClient);
}

void handleActors(char* topic, char* payload, AsyncMqttClientMessageProperties properties, size_t len, size_t index, size_t total) {
  if (led->isTopic(topic)) {
    led->onMqttMessage(topic, payload, properties, len, index, total);
  }
  if (led2->isTopic(topic)) {
    led2->onMqttMessage(topic, payload, properties, len, index, total);
  }
}




void connectToWifi() {
  Serial.println("Connecting to Wi-Fi...");
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
}

void onWifiConnect(const WiFiEventStationModeGotIP& event) {
  Serial.println("Connected to Wi-Fi.");
  connectToMqtt();
}

void onWifiDisconnect(const WiFiEventStationModeDisconnected& event) {
  Serial.println("Disconnected from Wi-Fi.");
  mqttReconnectTimer.detach(); // ensure we don't reconnect to MQTT while reconnecting to Wi-Fi
  wifiReconnectTimer.once(2, connectToWifi);
}

void connectToMqtt() {
  Serial.println("Connecting to MQTT...");
  mqttClient.connect();
}

void onMqttConnect(bool sessionPresent) {
  Serial.println("Connected to MQTT.");
}

void onMqttDisconnect(AsyncMqttClientDisconnectReason reason) {
  Serial.println("Disconnected from MQTT.");

  if (WiFi.isConnected()) {
    mqttReconnectTimer.once(2, connectToMqtt);
  }
}

void onMqttSubscribe(uint16_t packetId, uint8_t qos) {
  Serial.println("Subscribe acknowledged.");
  Serial.print("  packetId: ");
  Serial.println(packetId);
  Serial.print("  qos: ");
  Serial.println(qos);
}

void onMqttUnsubscribe(uint16_t packetId) {
  Serial.println("Unsubscribe acknowledged.");
  Serial.print("  packetId: ");
  Serial.println(packetId);
}

void onMqttMessage(char* topic, char* payload, AsyncMqttClientMessageProperties properties, size_t len, size_t index, size_t total) {
  Serial.println("Publish received.");
  Serial.print("  topic: ");
  Serial.println(topic);
  Serial.print("  payload: ");
  Serial.println(payload);
}

void onMqttPublish(uint16_t packetId) {
  Serial.println("Publish acknowledged.");
  Serial.print("  packetId: ");
  Serial.println(packetId);
}
