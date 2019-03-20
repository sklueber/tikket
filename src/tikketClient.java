import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


public class tikketClient {
    final private String tikketServerHost;
    final private int tikketServerPort;
    private int SrvVa_ID;
    private String SrvVa_name;

    public tikketClient(String ServerHost, int ServerPort) {
        tikketServerHost = ServerHost;
        tikketServerPort = ServerPort;

        Socket socketOfClient;
        BufferedWriter os;
        BufferedReader is;

        try {
            socketOfClient = new Socket(tikketServerHost, ServerPort);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host " + tikketServerHost);
            return;
        } catch (IOException e) {
            System.err.println("I/O Fehler " + tikketServerHost);
            return;
        }

        try {

            // Write data to the output stream of the Client Socket.
            os.write("veranstaltungAuslesen");
            // End of line
            os.newLine();
            // Flush data.
            os.flush();
            os.write("QUIT");
            os.newLine();
            os.flush();

            // Read data sent from the server.
            // By reading the input stream of the Client Socket.
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.contains("OK")) {
                    break;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }

        //Hier muss auch ein grundlegender Sync stattfinden
    }
}