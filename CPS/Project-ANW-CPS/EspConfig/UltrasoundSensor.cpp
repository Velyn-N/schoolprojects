#include <AsyncMqttClient.h>
#include "EspPins.h"

UltrasoundSensor::UltrasoundSensor(AsyncMqttClient* mqtt, String sensorName, String deviceName, int pinTrigger, int pinEcho)
{
  this->mqttClient = mqtt;
  this->topic = "nma/sensor/" + sensorName + "/" + deviceName;
  this->pinTrigger = pinTrigger;
  this->pinEcho = pinEcho;
  pinMode(pinTrigger, OUTPUT);
  pinMode(pinEcho, INPUT);
}

void UltrasoundSensor::loopSensor()
{
  digitalWrite(pinTrigger, LOW);
  delay(2);
  digitalWrite(pinTrigger, HIGH);
  delayMicroseconds(10); 
  digitalWrite(pinTrigger, LOW);

  long duration = pulseIn(echoPin, HIGH);

  long distance = duration/58.2;

  this->mqttClient->publish(this->topic.c_str(), 1, true, String(distance).c_str());
}