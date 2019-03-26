package com.tikket.tikketServer;/*
 * Informatikprojekt aus 2019. Erstellt von Simon, und Max.
 * Zuletzt bearbeitet 24.03.19 22:29.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;

public class Drucker {
    private static PrintService service;
    private static DocPrintJob job;

    public Drucker() {
        this.setup();
    }

    public static void main(String[] args) {
        new Drucker();
    }

    private void setup() { //TODO tausende mögliche Errors, v.a. bei Abbruch durch den User
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service1 : services) {
            System.out.println(service1.getName());
        }
        services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService svc = PrintServiceLookup.lookupDefaultPrintService();
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        service = ServiceUI.printDialog(null, 100, 100, services, svc, null, attrs);
        job = service.createPrintJob();
        System.out.println(service.getName());
        //this.drucken("MyBarcode.png");
    }

    public void drucken(String pDateiname) {
        String pfad = "./" + pDateiname;
        File f = new File(pfad);
        if (f.exists() && !f.isDirectory()) { //überprüft ob der übergebene Dateipfad überhaupt zu einer Datei führt
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            Doc doc = new SimpleDoc(new MyPrintable(pDateiname), flavor, null);
            try {
                job.print(doc, null);
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Dateipfad ist fehlerhaft");
        }

    }
}

class MyPrintable implements Printable {
    private ImageIcon printImage;

    MyPrintable(String pPfad) {
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