const int LED_1 = 16;
const int LED_2 = 4;
const int LED_3 = 2;

const int COLD_BLINK = 590;
const int COLD_LIMIT = 621;
const int WARM_BLINK = 745;
const int WARM_LIMIT = 776;

const long BLINK_INTERVAL = 500;

long blinkLastTime = 0;
int blinkLightOn = 0;

void setup() {
  Serial.begin(115200);
  pinMode(LED_1, OUTPUT);
  pinMode(LED_2, OUTPUT);
  pinMode(LED_3, OUTPUT);

  Serial.println("Program ready!");
}

void loop() {
  int sensorValue = analogRead(A0);

  Serial.print(sensorValue);

  if (sensorValue < COLD_BLINK) {
    digitalWrite(LED_1, HIGH);
    digitalWrite(LED_2, LOW);
    digitalWrite(LED_3, LOW);
    Serial.println(" COLD");
  } else if (sensorValue < COLD_LIMIT) {
    digitalWrite(LED_1, blink());
    digitalWrite(LED_2, LOW);
    digitalWrite(LED_3, LOW);
    Serial.println(" COLD(!)");
  } else if (sensorValue < WARM_BLINK) {
    digitalWrite(LED_1, LOW);
    digitalWrite(LED_2, HIGH);
    digitalWrite(LED_3, LOW);
    Serial.println(" WARM");
  } else if (sensorValue < WARM_LIMIT) {
    digitalWrite(LED_1, LOW);
    digitalWrite(LED_2, blink());
    digitalWrite(LED_3, LOW);
    Serial.println(" WARM(!)");
  } else {
    digitalWrite(LED_1, LOW);
    digitalWrite(LED_2, LOW);
    digitalWrite(LED_3, HIGH);
    Serial.println(" HOT");
  }
}

int blink() {
  long diff = millis() - blinkLastTime;
  if (diff >= BLINK_INTERVAL) {
    blinkLastTime = millis();
    blinkLightOn = !blinkLightOn;
  }
  return blinkLightOn;
}
