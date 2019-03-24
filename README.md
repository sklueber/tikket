# tikket
tikket (working title). Ein Informatik-Projekt von Simon Kleber, Nico Plump und Max Cockhausen

# Zielbestimmung
Tickets werden in Form eines Barcodes mit einer einzigartigen Nummer versehen, was Sicherheit vor Fälschungen bietet. Die ausgestellten bzw. eingelösten Tickets können mithilfe einer App, auf Android, und einer Software mit Hilfe eines Barcode-Scanners, auf Windows, überprüft werden. Es lässt sich eine Übersicht über gekaufte und eingelöste Tickets anzeigen.
Als Ausgabemedium stehen eine *Druckfunktion* und ein *E-Mail-Versand* zur Verfügung. Außerdem können *mehrere Veranstaltungen* mit der Software betreut werden.

* Tickets erzeugen (Im Vorverkauf sowie an der Abendkasse)
* Tickets überprüfen (über App auf Android und Windows)
* Elektronische Tickets (per E-Mail)
* Druckfunktion
* Datenbankverwaltung von mehreren Veranstaltungen
* Übersicht über verkaufte/eingelöste Tickets

# Client starten
1. tikketClient Methode aufrufen
2. IP und Port des Servers eingeben
3. Client wird gestartet

# Server starten
1. ServerStarten Methode aufrufen
2. Port der genutzt werden soll eingeben
3. tikket-Server wird gestartet. Meldet "tikket-Server wurde gestartet" im Terminal

# Protokoll Client -> Server
Command | Parameter | Response
--------|-----------|---------
ticketErstellen||-->>OK
ticketAusgeben||[ID][UUID][status]
ticketPruefen|[UUID]|-->>OK / -->>NOK
ticketAuslass|[UUID]|-->>OK / -->>NOK
ticketEinlass|[UUID]|-->>OK / -->>NOK
||
veranstalterErstellen|[name]|-->>OK
veranstalterAusgeben||[ID][name]
veranstalterLoeschen|[ID]|-->>OK / -->>NOK(Abhängigkeiten)
||
veranstaltungErstellen|[name][datum][ort][vr_ID]|-->>OK
veranstaltungAusgeben||[ID][name][datum][ort][vr_ID]
veranstaltungLoeschen|[ID]|-->>OK / -->>NOK (Abhängigkeiten)
veranstaltungSetzen|[ID]|-->>OK / -->>NOK (ID nicht gefunden)
veranstaltungAuslesen||[ID][name]
||
serverTest| |-->>OK
-->>QUIT | | -->>OK
