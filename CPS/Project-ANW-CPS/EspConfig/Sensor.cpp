#include "Sensor.h"
#include <AsyncMqttClient.h>
#include "EspPins.h"

Sensor::Sensor(AsyncMqttClient* mqtt, String sensorName, String deviceName)
{
	this->mqttClient = mqtt;
  this->topic = "nma/sensor/" + sensorName + "/" + deviceName;
}

Sensor::Sensor(AsyncMqttClient* mqtt, String sensorName, String deviceName, int pin)
{
  this->mqttClient = mqtt;
  this->topic = "nma/sensor/" + sensorName + "/" + deviceName;
  this->isDigital = true;
  this->pin = pin;

  pinMode(pin, INPUT);
}

void Sensor::loopSensor()
{
  int sensorValue;
  if (this->isDigital) {
    sensorValue = digitalRead(this->pin);
  } else {
    sensorValue = analogRead(A0);
  }
  
  Serial.println(sensorValue);

	this->mqttClient->publish(this->topic.c_str(), 1, true, String(sensorValue).c_str());
}
