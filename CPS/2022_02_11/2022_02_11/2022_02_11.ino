#include <Servo.h>
#include <math.h>

const int PWMRANGE = 255;
const int SERVOPORT = 15; //D8
const int LED_L = 2; //D4
const int LED_R = 16; //D0

const int MID = 90;

Servo servo;

void setup() {
  Serial.begin(115200);
  
  servo.attach(SERVOPORT);
  
  pinMode(LED_L, OUTPUT);
  pinMode(LED_R, OUTPUT);
}

void loop() {
  int sensorValue = analogRead(A0);

  Serial.println("#####");
  Serial.println(sensorValue);

  int servoValue = sensorValue * 180 / 1024;

  Serial.println(servoValue);

  servo.write(servoValue);
  int led_l_value = MID - min(MID, servoValue);
  analogWrite(LED_L, led_l_value * MID / PWMRANGE);
  Serial.println(led_l_value);

  int led_r_value = max(0, servoValue - MID);
  analogWrite(LED_R, led_r_value * MID / PWMRANGE);
  Serial.println(led_r_value);
  
  delay(250);
}
