package com.tikket.tikketClient;/*
 * Informatikprojekt aus 2019. Erstellt von Simon, und Max.
 * Zuletzt bearbeitet 24.03.19 22:29.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

public class TestklasseDieDurchEinfachGUIAufraufeErsetztWerdenMuss {
    public static void main(String[] args) {
        CodeGenerator.barcodeErstellen("456786669");
        //Drucker d = new Drucker();
        //d.drucken("MyBarcode.png");
       // Mailversand.main(new String[]{"info@max-stockhausen.de", "Die große tikket. (working title)-Launchparty"});
        //spamMail(25);
    }

    public static void spamMail(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            //Mailversand.main(new String[]{"info@max-stockhausen.de", "Sorry Mann"});
        }
    }
}
