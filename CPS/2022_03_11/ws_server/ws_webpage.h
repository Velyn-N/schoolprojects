const char html[] PROGMEM = R"=====(
<!doctype html>
<html lang="de">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">
  <title>Websocket Control</title>

  <style>
    #output {
      overflow-y: scroll;
      max-height: 350px;
      height: 5rem;
    }
  </style>
</head>

<body style="height=100% width=100%">
  Slider und Button<p></p>
  <input type="button" onclick="ledFn()">Blink LED <p>
  <p></p>

  <input type="range" id="inputSliderLED" min="0" max="255" value="0" step="5" oninput="showValueLED(this.value)" /> LED
  <br><br><span id="outputTextLED">0</span>
  <p></p>

  <input type="range" id="servoRange" min="0" max="180" value="90" oninput="sendServo()" /> Servo

  <br><br>
  Nachrichten vom Server:
  <span id="output"></span>





  <script>
    var ipValue;
    var connection = new WebSocket("ws://192.168.4.1:81");

    connection.onopen = function () {
      connection.send('ping');
    };

    connection.onerror = function (error) {
      console.log('WebSocket Error ', error);
    };

    connection.onmessage = function (e) {
      console.log('Server: ', e.data);
      displayServerMessage(e.data);
    };

    function ledFn() {
      var toSend = "LED";
      connection.send(toSend);
    };

    function showValueLED(newValue) {
      document.getElementById("outputTextLED").innerHTML = newValue;
      connection.send("x" + newValue);
    }

    function sendServo() {
      let servoVal = document.querySelector("#servoRange").value;
      console.log(servoVal);
      connection.send("s" + servoVal);
    }

    function displayServerMessage(msg) {
      let output = document.querySelector("#output");
      let now = new Date();
      let timedMsg = now.getHours() + ":" + now.getMinutes() + " > " + msg;
      //output.innerHTML = output.innerHTML + '<br>' + timedMsg;
      output.innerHTML = timedMsg;
    }
  </script>
</body>

</html>
)=====";
/**/
