const int LED = 2;
const int LED_2 = 14;
const int BTNONE = 16;
const int BTNTWO = 5;

long startTime = 0;
long ledTimer = 0;
int targetLed = 0;
int state = 0;

void setup() {
  Serial.begin(115200);
  pinMode(LED, OUTPUT);
  pinMode(LED_2, OUTPUT);
  pinMode(BTNONE, INPUT);
  pinMode(BTNTWO, INPUT);

  Serial.println("Program ready!");
}

void loop() {
  int btnStateOne = digitalRead(BTNONE);
  int btnStateTwo = digitalRead(BTNTWO);

  //Serial.println(btnStateOne);
  //Serial.println(btnStateTwo);
  
  if (btnStateOne && !state) {
    start();
  }

  if (state) {
    long duration = millis() - startTime;
    if (duration >= ledTimer) {
      digitalWrite(LED, HIGH);
      digitalWrite(LED_2, HIGH);
    }

    if (btnStateOne) {
      if (targetLed > 2) {
        stop(duration - ledTimer);
      } else {
        stop("Wrong Button");
      }
    }
    
    if (btnStateTwo) {
      if (targetLed <= 1) {
        stop(duration - ledTimer);
      } else {
        stop("Wrong Button");
      }
    }
  }
}

void start() {
  startTime = millis();
  ledTimer = random(5*1000);
  targetLed = random(1, 2);
  state = 1;
}


void stop(long duration) {
  digitalWrite(LED, LOW);
  digitalWrite(LED_2, LOW);
  Serial.println(duration);
  state = 0;
}

void stop(String duration) {
  digitalWrite(LED, LOW);
  digitalWrite(LED_2, LOW);
  Serial.println(duration);
  state = 0;
}
