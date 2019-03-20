public class TestklasseDieDurchEinfachGUIAufraufeErsetztWerdenMuss
{
public static void main(String args[]){
    CodeGenerator.barcodeErstellen("ey");
    Drucker d = new Drucker();
    d.drucken("MyBarcode.png");
}
}
