import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class Drucker {
private static PrintService service;
private static DocPrintJob job;

    public static void main(String[] args){

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service1 : services) {
            System.out.println(service1.getName());
        }
        services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        service = ServiceUI.printDialog(
                null, 100, 100, services, svc, null, attrs);

        job = service.createPrintJob();
        System.out.println(service.getName());
        Drucker.drucken("MyQRCode.png");
    }

   private static void drucken(String pDateiname){
        String pfad = "./" + pDateiname;
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        Doc doc = new SimpleDoc(new MyPrintable(pDateiname), flavor, null);
       try {
           job.print(doc, null);
       } catch (PrintException e) {
           e.printStackTrace();
       }
   }

}
class MyPrintable implements Printable {
    private ImageIcon printImage;
    MyPrintable(String pPfad){
        printImage = new javax.swing.ImageIcon("./" + pPfad);
    }


    public int print(Graphics g, PageFormat pf, int pageIndex) {
        Graphics2D g2d = (Graphics2D) g;
        g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
        if (pageIndex == 0) {
            double pageWidth = pf.getImageableWidth();
            double pageHeight = pf.getImageableHeight();
            double imageWidth = printImage.getIconWidth();
            double imageHeight = printImage.getIconHeight();
            double scaleX = pageWidth / imageWidth;
            double scaleY = pageHeight / imageHeight;
            double scaleFactor = Math.min(scaleX, scaleY);
            g2d.scale(scaleFactor, scaleFactor);
            g.drawImage(printImage.getImage(), 0, 0, null);
            return Printable.PAGE_EXISTS;
        }
        return Printable.NO_SUCH_PAGE;
    }
}