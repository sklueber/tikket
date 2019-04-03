# tikket
tikket (working title). Ein Informatik-Projekt von Simon Klüber und Max Stockhausen

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
Command | Parameter | Response | Status
--------|-----------|----------|-------
ticketErstellen||-->>OK|Fertig
ticketAusgeben||*[ID]//[UUID]//[status]|Fertig
ticketPruefen|[:UUID]|-->>TRUE / -->>FALSE|Fertig
ticketAuslass|[:UUID]|-->>OK / -->>NOK|In Bearbeitung
ticketEinlass|[:UUID]|-->>OK|In GUI
ticketSenden|[:Mail-Adresse][:UUID]||In Bearbeitung
|||
veranstalterErstellen|[:name]|-->>OK|Nicht geschrieben
veranstalterAusgeben||*[:ID]//[:name]|Nicht geschrieben
veranstalterLoeschen|[:ID]|-->>OK / -->>NOK(Abhängigkeiten)|Nicht geschrieben; extra
|||
veranstaltungErstellen|[:name][datum][ort][vr_ID]|-->>OK|Nicht geschrieben
veranstaltungAusgeben||*[ID]//[name]//[datum]//[ort]//[vr_ID]|In GUI
veranstaltungLoeschen|[:ID]|-->>OK / -->>NOK (Abhängigkeiten)|Nicht geschrieben; extra
veranstaltungSetzen|[:ID]|-->>OK / -->>NOK (ID nicht gefunden)|NOK fehlt noch
aktuelleVeranstaltungAuslesen||[ID]//[name]|Fertig
|||
serverTest| |-->>OK|Fertig
-->>QUIT | | -->>OK|Fertig

# Open-Source-Lizenzen
* Java Database Connectivity (JDBC): https://www.apache.org/licenses/LICENSE-2.0.txt
* ZXing ("Zebra Crossing"): https://www.apache.org/licenses/LICENSE-2.0.txt
* Java Mail API: https://oss.oracle.com/licenses/CDDL+GPL-1.1
* Java SE: https://javaee.github.io/glassfish/LICENSE
