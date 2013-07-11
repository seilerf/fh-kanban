# Architekturdokumentation

# 1. Einführung und Ziele

## 1.1. Aufgabenstellung
 Im Rahmen der Veranstaltung "Entwicklung eines Informationssystems im Team" soll ein Kanban-Brett erstellt werden, das ein 
Vorgehensmodell in der Softwareentwicklung darstellt. Ziel eines solchen Modells ist es, die Anzahl paralleler Arbeiten zu reduzieren und 
somit schnellere Durchlaufzeiten zu erreichen. Dadurch sollen Probleme schnell scihtbar gemacht werden.  
## 1.2. Qualitätsziele
## 1.3. Stakeholder


# 2. Randbedingungen

## 2.1. Technische Randbedingungen
## 2.2. Organisatorische Randbedingungen
## 2.3. Konventionen

# 3. Kontextabgrenzung

## 3.1. Fachlicher Kontext
## 3.2. Technischer Kontext
## 3.3. Externe Schnittstellen

# 4. Lösungsstrategie

# 5. Bausteinsicht

# 6. Laufzeitsicht

# 7. Konzepte
## 7.1 Fachliche Strukturen
## 7.2 Typische Muster und Strukturen
## 7.3. Ausnahme- und Fehlerbehandlung
## 7.4. Bedienoberfläche
## 7.5. Ergonomie
## 7.6. Geschäftsregeln
## 7.7. Konfiguration
Das Kanban System bietet eine Möglichkeit verschiedene Sachen auf dem Board zur Laufzeit einszustellen. 
Hierfür gibt es ein Menüpunkt Einstellungen. Beim öffnen dieses Menüpunktes erscheint ein Fenster, welches diverse Einstellungsmöglichkeiten bietet.
Es ist möglich den Namen des Boards zu ändern. Durch betätigen des Namen Speicherns Button wird dies wirksam.
Dazu kann man noch die 4 verschiedenen Zeitbeschränkungs-Farben einstellen. Dies wird ohne Bestätigung sofort umgesetzt. Zusätzlich ist es möglich,
Das Karten-Limit pro Spalte einzustellen. Das heißt wie viele Karten pro Spalte sich maximal dort befinden dürfen.

## 7.8. Logging, Protokollierung
## 7.9. Management und Administrierbarkeit
## 7.10. Persistenz
Ein Kanban-Brett kann mit diesem Programm persistent gespeichert werden. Dazu öffnet man in der Menüleisete das Feld "Datei" und wählt 
dann "Board speichern...". Es öffnet sich ein Dialog, in dem man den Speicherort angeben kann. 

Die Speicherung kann in Form einer XML-Datei erfolgen. Ein DataManager erkennt, in welchem Format die Datei gespeichert werden soll und 
ruft den dazu notwendigen Parser auf. Bei der Speicherung im XML-Format wird dann ein XMLParser aufgerufen, der als Parameter das gesamte 
Board übergeben bekommt und dann alle Spalten und deren Karten durchläuft und in XML-Schreibweise parst. Als Zieldatei kann eine beliebige Datei 
angegeben oder auch neu erstellt werden, sofern der User die Berechtigung hat, auf das Verzeichnis, in dem gespeichert werden soll, zuzugreifen. 

Außerdem kann die Speicherung im CSV-Format und im HTML-Format erfolgen. 
## 7.11. Plausibilisierung und Validierung
## 7.12. Transaktionsbehandlung