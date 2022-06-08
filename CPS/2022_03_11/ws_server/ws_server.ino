#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <WebSocketsServer.h>
#include <ESP8266WebServer.h>
//#include <ESPAsyncTCP.h>
#include "ws_webpage.h"
#include <Servo.h>

ESP8266WebServer server(80);
WebSocketsServer webSocket = WebSocketsServer(81);

const char *ssid = "5G Covid Control Unit #69F0R20";
const char *password = "Mittwoch";

const int ledPinD8 = 15;
const int ledPinD0 = 16;

Servo servo;
#define Servo1 5
// Pin D1

bool con = false;
int count;
bool blinky = false;

void entry_page()
{
  String socketIP = WiFi.softAPIP().toString() + ":81";
  Serial.println("Socket IP: " + socketIP);
  server.send(200, "text/html", html);
}

uint8_t wsnum;

void webSocketEvent(uint8_t num, WStype_t type, uint8_t *payload, size_t lenght)
{

  switch (type)
  {
  case WStype_DISCONNECTED:
    con = false;
    break;
  case WStype_CONNECTED:
  {
    IPAddress ip = webSocket.remoteIP(num);
    con = true;
    Serial.println("Websocket Connected!");

    wsnum = num;
  }
  break;
  case WStype_TEXT:
  {
    String text = String((char *)&payload[0]);
    if (text == "LED")
    {
      blinky = true;
      Serial.println("led blinkt");
    }

    if (text.startsWith("x"))
    {
      String xVal = (text.substring(text.indexOf("x") + 1, text.length()));
      int xInt = xVal.toInt();
      analogWrite(ledPinD8, xInt);
      Serial.println(xVal);
    }

    if (text.startsWith("s"))
    {
      String xVal = (text.substring(text.indexOf("s") + 1, text.length()));
      int xInt = xVal.toInt();
      if (xInt >= 0 && xInt <= 255)
      {
        servo.write(xInt);
      }
      Serial.println(xVal);
    }
  }
  break;
  }
}

void setup()
{
  Serial.begin(115200);
  pinMode(ledPinD0, OUTPUT);
  WiFi.mode(WIFI_AP);
  WiFi.softAP(ssid, password);
  server.on("/", entry_page);
  server.begin();
  Serial.println("HTTP server gestartet!");

  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);
  webSocket.begin();
  webSocket.onEvent(webSocketEvent);

  servo.attach(Servo1);
}

int lastSendValue;

void loop()
{
  if (blinky)
  {
    digitalWrite(ledPinD0, HIGH);
    blinky = false;
  }
  else
  {
    if (count % 50 == 0)
      digitalWrite(ledPinD0, LOW);
  }

  Serial.println("######");
  if (con)
  {
    int val = analogRead(A0);
    Serial.println(val);
    Serial.println(lastSendValue);
    if (val != lastSendValue) {
      lastSendValue = val;
      webSocket.sendTXT(wsnum, "A0 = " + val);
      Serial.println("A0 = " + val);
    }
  }
  Serial.println(con);
  delay(1000);

  count++;
  webSocket.loop();
  server.handleClient();
  delay(10);
}
