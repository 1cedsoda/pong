# WinfPong
Dieses Projekt entstand im Modul "Programmierung II".
Es ist ein Pong-Spiel mit Multiplayer Funktionalität.
Der Server unterstützt mehrere Spieler und gleichzeitige Spiele.

# Spielen
## 1. Spiel Starten

### Variante 1: Mit JAR
Gehe in den Ordner `bin` und doppelklicke die Datei `winfpong-client.jar`.
Unter Mac/Linux führe vorher noch das kommando `chmod +x winfpong-client.jar` aus
Alternativ kannst du die Jar auch direkt im Termial starten:
```shell
java -jar ./bin/winfpong-client.jar
```
### Variante 2: Mit IntelliJ
Alternativ öffne das Projekt mit IntelliJ und starte die konfiguration `Client` oben rechts.
## 2. Server beitreten
Gebe den hostname eines Servers ein und drücke `Join`.
Der Port ist standardmäßig der Port `2347`.
Auch deinen Spielernamen kannst du spezifizieren. Dieser wird persistent in der `.winfpong` Datei in deinem Nutzerordner gespeichert und wieder geladen wenn du das Spiel neu startest.
## 3. Spielen
Drücke `Create Game` und warte bis ein anderer Spieler beitritt oder klicke `Join` neben einem Spielernamen um einem Spiel beizutreten.

# Server Starten

## Variante 1: JAR
Gehe in den Ordner `bin` und doppelklicke die Datei `winfpong-server.jar`.
Unter Mac/Linux führe vorher noch das kommando `chmod +x winfpong-server.jar` aus
Alternativ kannst du die Jar auch direkt im Termial starten:
```shell
java -jar ./bin/winfpong-server.jar
```

## Variante 2: IntelliJ
Wähle die Run Configuration `Server`aus und starte das Programm.

## Variante 3: Mit Docker
Ein Docker Image kann mit folgendem Befehl erstellt werden:
```shell
docker build . --tag winfpong-server
```
Das Docker Image kann mit folgendem Befehl gestartet werden:
```shell
docker run -p 2347:2347 -p 2347:2347/udp -d winfpong-server
```

# winfpong.ddns.net
Dies ist eine öffentliche Server Instanz die im Client voreingestellt ist.
Leider hat dieser Server einen Fehler wodurch das Spielfeld auf dem Client nicht aktualisiert wird.
Der Server schickt aus unerklärlichen Gründen und trotz gleicher Codebase zu einem lokalen Server immer die SpieleID `0?x`.
Um das Spiel zu testen muss leider ein lokaler Server gestartet werden.

# Bekannte Fehler
## Schläger bewegen sich nicht
Nach dem Beitritt zu einem Spiel musst du einmal das Fenster de-fokussieren und durch einen Klick wieder fokussieren.

## Ball wird in unnatürliche Richtung reflektiert
Die Reflexionswinkel werden physikalisch berechnet und enthalten eine Zufallskomponente.
Die Zufallskomponente führt bei steilen Kollisionen manchmal zu einer unnatürlichen Reflexionsrichtung.
Dieses Verhalten wir außerdem verstärkt da die Reflexionswinkel ±10° zur Lotrechten des Hindernisses gesperrt wurden.

## Client crasht beim Verbinden, wenn der Server nicht erreichbar ist.
Falls ein Spielserver unter dem eingegebenem Host+Port nicht erreichbar ist, stürzt der Client beim Verbindungsaufbau ab.

## Client merkt nicht, wenn die Verbindung zu Server abbricht.
Wenn der Server gestoppt wird, erkennt dies ein verbundener Client nicht.
Die GUI wird nicht mehr aktualisiert. Der Client kann sich jedoch neu verbinden und alles ist wieder normal.

## Der Ball fliegt schneller horizontal als vertikal
In der Berechnung bewegt sich der Ball in einem Feld von x -1 bis +1 und y -1 bis +1.
Das Spielfeld wird als breites Rechteck dargestellt, weshalb die Geschwindigkeit verzerrt wird.

## Spiel läuft weiter, wenn ein Spieler das Spiel verlässt
Wenn das Spiel bereits läuft und ein Spieler das Spiel verlässt, wird das Spiel weiterlaufen. Wenn der zweite Spieler das Spiel nun auch verlässt, wird es vollständig gestoppt
