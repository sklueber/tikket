package com.tikket.tikketscan;

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

import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class tikketClient { //funktioniert nicht auf Android, nur zur Referenz für die Methoden
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
    }

    public void socketErstellen() {
        try {
            socketOfClient = new Socket(tikketServerHost, tikketServerPort);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        } catch (UnknownHostException e) {
            Log.d("myTag", "Unbekannter Host: " + tikketServerHost);
        } catch (IOException e) {
            Log.d("myTag", "I/O Fehler: " + tikketServerHost);
            Log.d("myTag", e.getStackTrace()[0].toString());
        }
    }

    public void firstSync() {
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
                    Log.d("myTag", "Alle Sockets werden geschlossen");
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
                Log.d("myTag", "Server: " + responseLine);
                if (responseLine.equals("-->>OK")) {
                    return;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            Log.d("myTag", "Server nicht gefunden: " + e);
        } catch (IOException e) {
            Log.d("myTag", "I/O Fehler:  " + e);
        } catch (NullPointerException e) {
            Log.d("myTag", "NPE; Vermutlich wurde kein Socket gefunden: " + e);
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

            Log.d("myTag", "ServerSocket erstellt");
        } catch (UnknownHostException e) {
            Log.d("myTag", "Unbekannter Host: " + tikketServerHost);
            return false;
        } catch (IOException e) {
            Log.d("myTag", "I/O Fehler: " + tikketServerHost);
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
                Log.d("myTag", "Server: " + responseLine);
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
            Log.d("myTag", "Unbekannter Host: " + e);
            return false;
        } catch (IOException e) {
            Log.d("myTag", "I/O Fehler:  " + e);
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