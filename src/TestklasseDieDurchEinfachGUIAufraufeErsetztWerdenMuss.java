public class TestklasseDieDurchEinfachGUIAufraufeErsetztWerdenMuss
{
public static void main(String[] args) {
    //CodeGenerator.barcodeErstellen("warum scannst du das? haste nichts besseres zu tun?");
    //Drucker d = new Drucker();
    //d.drucken("MyBarcode.png");
    Mailversand.main(new String[]{"s3.hans@web.de", "Die große tikket. (working title)-Launchparty"});
    //spamMail(100);
    //testTxtLesen();
}
public static void spamMail(int anzahl) { //tu es nicht
    for(int i = 0; i < anzahl; i++){
        Mailversand.main(new String[]{"jbr78421@zoqqa.com", "Sorry Mann"});
}
}
}
