#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>

const char* ssid     = "5G Covid Control Unit 0X69F20A";
const char* password = "Mittwoch";

ESP8266WebServer server(80);        // Der Server auf Port Nummer 80
IPAddress apIP(192, 168, 4, 1);


String request = "";
int LED_Pin = 16;
int LED_2_Pin = 2;
int ADC;

void setup() {
  Serial.begin(115200);       // Initialisieren der seriellen Schnittstelle

  pinMode(LED_Pin, OUTPUT); 
  pinMode(LED_2_Pin, OUTPUT);
  delay(2000);

  Serial.println("Setting up Access Point...");
  WiFi.mode(WIFI_AP);
  WiFi.softAP(ssid, password);
  
  delay(100);
  
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);

  server.on("/", handleRoot);
  server.on("/SERVO", handleServoForm);
  server.on("/LED1ON", ledOneOn);
  server.on("/LED1OFF", ledOneOff);
  server.on("/LED2ON", ledTwoOn);
  server.on("/LED2OFF", ledTwoOff);
  server.begin();

  Serial.println("HTTP Server gestartet");
}

void handleRoot() {
  server.send(200, "text/html", getFullHtmlDoc());
}

void loop() {
  server.handleClient();
}

void redirectToRoot() {
  server.sendHeader("Location","/");
  server.send(303);
}

void ledOneOn() {
  digitalWrite(LED_Pin, HIGH);
  redirectToRoot();
}

void ledOneOff() {
  digitalWrite(LED_Pin, LOW);
  redirectToRoot();
}

void ledTwoOn() {
  digitalWrite(LED_2_Pin, HIGH);
  redirectToRoot();
}

void ledTwoOff() {
  digitalWrite(LED_2_Pin, LOW);
  redirectToRoot();
}

void handleServoForm() {
  Serial.println(server.arg("servospeed"));
}

String getFullHtmlDoc() {
  return getFullHtmlDoc(digitalRead(LED_Pin) == HIGH, digitalRead(LED_2_Pin) == HIGH);
}

String getFullHtmlDoc(boolean ledOne, boolean ledTwo) {
  String serialOut = "Led1=";
  serialOut += ledOne;
  serialOut += ",Led2=";
  serialOut += ledTwo;
  Serial.println(serialOut);
  
  String html = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
  html += "<!DOCTYPE html><html><head>";
  html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'/><meta charset='utf-8'><meta http-equiv='refresh' content='3'>";
  html += "<style>body {font-size:150%; text-align: center;}";
  html += "#main {display: table; margin: auto;  padding: 0 10px 0 10px; } h2,{text-align:center; }";
  html += ".button { padding:10px 10px 10px 10px; width:100%;  background-color: #66cc66; font-size: 120%;}</style>";
  html += "<title>Server Control</title>";
  html += "</head><body><div id='main'><h2>Server Control</h2>";
  
  html += "<table><tr><td colspan='2'>LED One is ";
  html += (ledOne) ? "on" : "off";
  html += "</td></tr><tr><td>";
  html += "<form id='1on' action='LED1ON'><input class='button' type='submit' value='LED ON' ></form>";
  html += "</td><td>";
  html += "<form id='1off' action='LED1OFF'><input class='button' type='submit' value='LED OFF' ></form>";
  html += "</td></tr></table>";

  html += "<table><tr><td colspan='2'>LED Two is ";
  html += (ledTwo) ? "on" : "off";
  html += "</td></tr><tr><td>";
  html += "<form id='2on' action='LED2ON'><input class='button' type='submit' value='LED ON' ></form>";
  html += "</td><td>";
  html += "<form id='2off' action='LED2OFF'><input class='button' type='submit' value='LED OFF' ></form>";
  html += "</td></tr></table>";

  html += "<table><tr><td colspan='2'>ServoControls</td></tr>";
  html += "<tr><td><form id='servo' action='SERVO'>";
  html += "<input type='range' name='servospeed'><input class='button' type='submit' value='LED ON'>";
  html += "</form></td></tr></table>";

  html += "</div></body></html>";

  return html;
}
