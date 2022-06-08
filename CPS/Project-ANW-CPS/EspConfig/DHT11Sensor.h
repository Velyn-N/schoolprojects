#include <AsyncMqttClient.h>

class DHT11Sensor 
{
  public:
    DHT11Sensor(AsyncMqttClient* mqtt, String sensorNameTemperature, String sensorNameHumidity, String deviceName, int pin);

    void loopSensor();

  private:
    String topicTemp;
    String topicHum;
    AsyncMqttClient* mqttClient;
    int pin;
};
