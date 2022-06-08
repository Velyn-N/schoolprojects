#include <AsyncMqttClient.h>

class Actor
{
  public:
    Actor(String channelName, String deviceName, int pin);
    Actor(String channelName, String deviceName, int pin, boolean isDigital);
    
    void onMqttMessage(char* topic, char* payload, AsyncMqttClientMessageProperties properties, size_t len, size_t index, size_t total);
    boolean isTopic(char* topic);
    void subscribeToMqtt(AsyncMqttClient* mqtt);

  private:
    String topic;
    boolean isDigital;
    int pin;
};
