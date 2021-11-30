1 Applikation starten:

1.1
shapes-1.0.0-SNAPSHOT-runner.exe ausführen (per Doppelklick oder Cmd)

1.2
target/shapes-1.0.0-SNAPSHOT-runner.jar ausführen (Benötigt eine JDK17) 

1.3
Eine Windows-Cmd öffnen. Anschließend mvnw compile quarkus:dev ausführen. Die Applikation wird im Dev-Modus gestartet.
Benötigt Java 11 JDK oder neuer, prüfen per java -version innerhalb der Cmd.


2 Applikation nutzen:

Nach dem Start im Browser localhost:8080 aufrufen. Anschließend finden sich in der Console wo die Applikation gestartet wurde sowie in der WebConsole die Nachricht "WebSocket connected". Nun ist die Applikation nach Belieben nutzbar.


3 Der Sourcecode

3.1
Der Java-Sourcecode befindet sich unter src/main/java/.
Die Web-Klassen befinden sich direkt unter ../de/nmadev/ und die Aufgabenbezogenen Klassen unter ../de/nmadev/shapes/.

Liste:
WebOut - WebSocket basierende Klasse für die Web-Console. Beinhaltet einen REST-Endpoint für den Clear der Console.
ShapeManager - REST-Endpoints für die Übermittlung der Shapes. Steuert die Applikation auf Backend-Seite.

Die Klassen im Shapes-Subpackage sind selbsterklärend, da nach Aufgabenstellung entwickelt.

3.2
Der Webinterface-Sourcecode befindet sich unter src/main/resources/META-INF/resources/. Die direkt hier liegende index.html ist die primäre Quelldatei, unter ../lib/ liegen die Javascript-, Css- und Dependency-Dateien.

Hier eine Liste dazu:
Selfmade:
actionBindings.js - Verbindet HTML-Elemente mit ihren Funktionen
script.js - Selbstgeschriebene Javascript-Datei die zwischen Front- und Backend kommuniziert sowie die Zeichnungsfunktionen des Frontends steuert.
style.css - Selbstgeschriebene CSS-Datei für ein paar Designanpassungen.
favicon.png - Das Favicon der Seite

Dependencies:
jquery-3.5.1.min.js - jQuery Javascript Library. Verkürzt Funktionsaufrufe. Siehe https://jquery.com/
materialize.min.css & materialize.min.js - Dependency für ein paar Grundlagen des Web-Layouts. Siehe https://materializecss.com/
