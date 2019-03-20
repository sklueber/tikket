import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    private static void generateBarcodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        Code128Writer BarcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = BarcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        try {
            generateBarcodeImage("This is my first Barcode leeeel", 350, 130, QR_CODE_IMAGE_PATH); //TODO Parameter einbauen damit Ticketnummer zum Barcode wird
        } catch (WriterException e) {
            System.out.println("Could not generate Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate Code, IOException :: " + e.getMessage());
        }
    }
}
