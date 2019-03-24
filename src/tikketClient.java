import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class tikketClient {
    public void setTikketServerHost(String tikketServerHost) {
        this.tikketServerHost = tikketServerHost;
    }

    public void setTikketServerPort(int tikketServerPort) {
        this.tikketServerPort = tikketServerPort;
    }

    private String tikketServerHost;
    private int tikketServerPort;
    //VA, die der Client bedient
    private int SrvVa_ID;
    private String SrvVa_name;
    //Serverstuff
    Socket socketOfClient;
    BufferedWriter os;
    BufferedReader is;

    public static void main(String[] args) {
        new tikketClient();
    }

    public tikketClient() {
        new ClientStarten(this);

        //TODO Hier muss auch ein grundlegender Sync stattfinden
    }

    public void GUIstarten() {
        new StartseiteGUI(this);
    }

    public void ticketErstellen() {
        try {
            socketOfClient = new Socket(tikketServerHost, tikketServerPort);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            System.out.println("ServerSocket erstellt");
        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host: " + tikketServerHost);
            return;
        } catch (IOException e) {
            System.err.println("I/O Fehler: " + tikketServerHost);
            return;
        }

        try {
            // In OutputStream schreiben, senden
            os.write("ticketErstellen");
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.equals("OK")) {
                    return;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host: " + e);
            return;
        } catch (IOException e) {
            System.err.println("I/O Fehler:  " + e);
            return;
        }
    }

    private String ticketAusgeben() {
        return null;
    }

    //Prüft ob das gegebene Ticket gültig ist. Wenn ja wird true zurückgegeben.
    public boolean ticketPruefen(int scan_UUID) {
        try {
            socketOfClient = new Socket(tikketServerHost, tikketServerPort);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            System.out.println("ServerSocket erstellt");
        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host: " + tikketServerHost);
            return false;
        } catch (IOException e) {
            System.err.println("I/O Fehler: " + tikketServerHost);
            return false;
        }

        try {
            // In OutputStream schreiben, senden
            os.write("ticketPruefen;" + scan_UUID);
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.equals("true")) {
                    return true;
                } else if (responseLine.equals("false")) {
                    return false;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host: " + e);
            return false;
        } catch (IOException e) {
            System.err.println("I/O Fehler:  " + e);
            return false;
        }
        return false;
    }

    private boolean ticketAuslass() {
        return false;
    }

    private void ticketEinlass() {
    }

    private void veranstalterErstellen() {
    }

    private void veranstalterAusgeben() {
    }

    private void veranstalterLoeschen() {
    }

    private void veranstaltungErstellen() {
    }

    private void veranstaltungAusgeben() {
    }

    private void veranstaltungLoeschen() {
    }

    private void veranstaltungSetzen() {
    }

    public int verantaltungIDausgeben() {
        return SrvVa_ID;
    }

    public String veranstaltungNameAusgeben() {
        return SrvVa_name;
    }
}