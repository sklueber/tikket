import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class tikketClient {
    public tikketClient(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);

            OutputStream output = socket.getOutputStream();
            PrintStream ps = new PrintStream(output, true);
            ps.println("Hallo Welt!");
            ps.println("Hallo Otto!");

            InputStream input = socket.getInputStream();
            System.out.println("verfügbare Bytes: " + input.available());
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));

            while (buff.ready()) {
                System.out.println(buff.readLine());
            }

        } catch (UnknownHostException e) {
            System.out.println("Server nicht gefunden...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProbleme...");
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    System.out.println("Socket geschlossen...");
                } catch (IOException e) {
                    System.out.println("Socket nicht zu schliessen...");
                    e.printStackTrace();
                }
        }
    }
}
