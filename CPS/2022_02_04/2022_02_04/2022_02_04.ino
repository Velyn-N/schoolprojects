const int LED = 16;
const int PWMRANGE = 255;

void setup() {
  Serial.begin(115200);
  pinMode(LED, OUTPUT);
}

void loop() {
  int sensorValue = analogRead(A0);

  Serial.println(sensorValue);
  
  if (sensorValue < 256) { // Sensibilisieren
    int pwmValue = PWMRANGE - sensorValue;
    analogWrite(LED, pwmValue);
  } else {
    analogWrite(LED, 0);
  }
}
