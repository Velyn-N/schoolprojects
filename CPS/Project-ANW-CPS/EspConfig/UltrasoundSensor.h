#include <AsyncMqttClient.h>

class UltrasoundSensor 
{
  public:
    UltrasoundSensor(AsyncMqttClient* mqtt, String sensorName, String deviceName, int pinTrigger, int pinEcho);

    void loopSensor();

  private:
    String topic;
    String topicHum;
    AsyncMqttClient* mqttClient;
    int pinTrigger;
    int pinEcho;
};