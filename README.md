# WinfPong
Dieses Projekt entstand im Modul "Programmierung II".
Es ist ein Pong-Spiel mit Multiplayer Funktionalität.
Der Server unterstützt mehrere Spieler und gleichzeitige Spiele.

# Spielen
## 1. Starten
Um das spiel zu starten, öffne das Projekt in IntelliJ und starte das Programm "Client".
## 2. Server beitreten
Gebe den hostname eines Servers ein und drücke `Join`.
Der Port ist standardmäßig der Port `2347`.
## 3. Spiel starten
Drücke `Create Game` und warte bis ein anderer Spieler beitritt oder klicke `Join` neben einem Spielernamen.

# Server Starten
## Via IntelliJ
Wähle die Run Configuration `Server`aus und starte das Programm.

## Via Terminal
```shell
java -jar ./bin/winfpong-server.jar
```

## Via Docker
Ein Docker image kann mit folgenden Befehlen erstellt werden:
```shell
docker build  . --tag winfpong-server
```
Das Docker Image kann mit folgenden Befehlen gestartet werden:
```shell
docker run -p 2347:2347 -d winfpong-server
```

# Bekannte Fehler
## Ball  wird in unnatürliche Richtung reflektiert
Die Reflexionswinkel werden physikalisch berechnet und enthält eine Zufallskomponente.
Die Zufallskomponente führt bei steilen Kollisionen teilweise zu einer unnatürlichen Reflexionsrichtung.

## Client crasht beim Verbinden, wenn der Server nicht erreichbar ist.
Falls ein Spielserver unter dem eingegebenem Host+Port nicht erreichbar ist, stürzt der Client beim Verbindungsaufbau ab.

## Client merkt nicht, wenn die Verbindung zu Server abbricht.
Wenn der Server gestoppt wird, erkennt dies ein verbundener Client nicht.
Die GUI wird nicht mehr aktualisiert. Der Client kann sich jedoch neu verbinden und alles ist wieder normal.