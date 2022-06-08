const int LED = 16;
const int BUZZER = 2;

void setup() {
  Serial.begin(115200);
  pinMode(LED, OUTPUT);
}

void loop() {
  int sensorValue = analogRead(A0);

  Serial.println(sensorValue);

  if (sensorValue < 512) {
    digitalWrite(LED, HIGH);
  } else {
    digitalWrite(LED, LOW);
  }

  tone(BUZZER, sensorValue * 15);
}
