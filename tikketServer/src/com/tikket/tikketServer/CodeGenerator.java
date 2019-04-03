/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 03.04.19 05:57 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketServer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class CodeGenerator {
    private static final String barcodePfad = "./raw_Barcode.png";

    private static void generateBarcodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        Code128Writer BarcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = BarcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void barcodeErstellen(String pTicketnummer) {
        try {
            generateBarcodeImage(pTicketnummer, 700, 260, barcodePfad);
        } catch (WriterException e) {
            System.out.println("Could not generate Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate Code, IOException :: " + e.getMessage());
        }

        File path = new File("./"); // base path of the images

        // load source images
        BufferedImage image = null;
        BufferedImage overlay = null;

        {
            try {
                image = ImageIO.read(new File(path, barcodePfad));
                overlay = ImageIO.read(CodeGenerator.class.getClassLoader().getResource("barcode/overlay.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // create the new image, canvas size is the max. of both image sizes
        BufferedImage combined = new BufferedImage(750, 260, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);

// Save as new image
        try {
            ImageIO.write(combined, "PNG", new File(path, "Tikket_Barcode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
