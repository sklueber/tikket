/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:24.
 * Zuletzt bearbeitet 23.03.19 18:07.
 * Keiner klaut das hier! (c) 2019.
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CodeGenerator {
    private static final String barcodePfad = "./MyBarcode.png";

    private static void generateBarcodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        Code128Writer BarcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = BarcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void barcodeErstellen(String pTicketnummer) {
        try {
            generateBarcodeImage(pTicketnummer, 350, 130, barcodePfad);
        } catch (WriterException e) {
            System.out.println("Could not generate Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate Code, IOException :: " + e.getMessage());
        }
    }
}
