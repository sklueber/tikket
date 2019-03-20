import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.*;

public class Drucker {
private PrintService service;
private DocPrintJob job;

    public static void main(String[] args){
        service = PrintServiceLookup.lookupDefaultPrintService();
        job = service.createPrintJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (int i = 0; i < services.length; i++) {
            System.out.println(services[i].getName());
        }
    }

    public void drucken(String pDateiname){
        String pfad = pDateiname
                "http://www.apress.com/ApressCorporate/supplement/1/421/bcm.gif ");
        DocFlavor flavor = DocFlavor.URL.GIF;
        Doc doc = new SimpleDoc(url, flavor, null);
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        attrs.add(new Copies(2));
        job.print(doc, attrs);
    }

}