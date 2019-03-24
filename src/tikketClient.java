/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico.
 * Zuletzt bearbeitet 24.03.19 23:36.
 * Keiner klaut das hier! (c) 2019.
 */

/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:28.
 * Zuletzt bearbeitet 24.03.19 22:24.
 * Keiner klaut das hier! (c) 2019.
 */

/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:21.
 * Zuletzt bearbeitet 24.03.19 22:19.
 * Keiner klaut das hier! (c) 2019.
 */

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
    }

    public void socketErstellen() {
        try {
            socketOfClient = new Socket(tikketServerHost, tikketServerPort);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unbekannter Host: " + tikketServerHost);
        } catch (IOException e) {
            System.err.println("I/O Fehler: " + tikketServerHost);
        }
    }

    public void firstSync() {
    }

    public void starten() {
        new StartseiteGUI(this);
    }

    public void beenden() {
        try {
            os.write("-->>QUIT");
            os.newLine();
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                if (responseLine.equals("-->>OK")) {
                    System.out.println("Alle Sockets werden geschlossen");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ticketErstellen() {
        try {
            // In OutputStream schreiben, senden
            os.write("ticketErstellen");
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.equals("-->>OK")) {
                    return;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Server nicht gefunden: " + e);
        } catch (IOException e) {
            System.err.println("I/O Fehler:  " + e);
        } catch (NullPointerException e) {
            System.out.println("NPE; Vermutlich wurde kein Socket gefunden: " + e);
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

    public void veranstaltungAusgeben() {
        try {
            // In OutputStream schreiben, senden
            os.write("veranstaltungAusgeben");
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;

            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.contains("-->>OK")) {
                    return;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Server nicht gefunden: " + e);
        } catch (IOException e) {
            System.err.println("I/O Fehler:  " + e);
        } catch (NullPointerException e) {
            System.out.println("NPE; Vermutlich wurde kein Socket gefunden: " + e);
        }
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