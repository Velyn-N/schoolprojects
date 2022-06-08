#include <AsyncMqttClient.h>

class Sensor
{
	public:
		Sensor(AsyncMqttClient* mqtt, String sensorName, String deviceName);
    Sensor(AsyncMqttClient* mqtt, String sensorName, String deviceName, int pin);

		void loopSensor();

	protected:
		AsyncMqttClient* mqttClient;
		String topic;
    boolean isDigital;
    int pin;
};
