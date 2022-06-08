#include <DHT.h>
#include "DHT11Sensor.h"
#include <AsyncMqttClient.h>
#include "EspPins.h"

DHT11Sensor::DHT11Sensor(AsyncMqttClient* mqtt, String sensorNameTemperature, String sensorNameHumidity, String deviceName, int pin)
{
  this->mqttClient = mqtt;
  this->topicTemp = "nma/sensor/" + sensorNameTemperature + "/" + deviceName;
  this->topicHum = "nma/sensor/" + sensorNameHumidity + "/" + deviceName;
  this->pin = pin;
}

void DHT11Sensor::loopSensor()
{
  // Wird jedes Mal erstellt, da eine Feldzuweisung zu Fehlern führt
  DHT dht(this->pin, DHT11);
  this->mqttClient->publish(this->topicTemp.c_str(), 1, true, String(dht.readTemperature()).c_str());
  this->mqttClient->publish(this->topicHum.c_str(), 1, true, String(dht.readHumidity()).c_str());
}
