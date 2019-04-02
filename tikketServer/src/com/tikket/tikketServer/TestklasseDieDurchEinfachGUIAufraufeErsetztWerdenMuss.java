/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 02.04.19 03:29 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketServer;

public class TestklasseDieDurchEinfachGUIAufraufeErsetztWerdenMuss {
    public static void main(String[] args) {
        CodeGenerator.barcodeErstellen("123456789");
        //Drucker d = new Drucker();
        //d.drucken("MyBarcode.png");
        Mailversand.main(new String[]{"info@max-stockhausen.de", "Ihr digitales Ticket"});
    }

    public static void spamMail(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            Mailversand.main(new String[]{"info@max-stockhausen.de", "Sorry Mann"});
        }
    }
}
