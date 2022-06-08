#include "Actor.h"
#include <AsyncMqttClient.h>

Actor::Actor(String sensorName, String deviceName, int pin)
{
  this->topic = "nma/actor/" + sensorName + "/" + deviceName;
  this->isDigital = false;
  this->pin = pin;
}

Actor::Actor(String sensorName, String deviceName, int pin, boolean isDigital)
{
  this->topic = "nma/actor/" + sensorName + "/" + deviceName;
  this->isDigital = isDigital;
  this->pin = pin;

  if (isDigital) {
    pinMode(pin, OUTPUT);
  }
}

void Actor::subscribeToMqtt(AsyncMqttClient* mqtt) {
  mqtt->subscribe(this->topic.c_str(), 1);
}

boolean Actor::isTopic(char* topic) {
  return (this->topic == topic);
}

void Actor::onMqttMessage(char* topic, char* payload, AsyncMqttClientMessageProperties properties, size_t len, size_t index, size_t total)
{
  int iPayload = atoi(payload);
  
  if (this->isDigital) {
    digitalWrite(this->pin, ((iPayload > 0) ? HIGH : LOW));
  } else {
    if (0 <= iPayload < 256) {
      analogWrite(this->pin, iPayload);
    }
  }
}
