/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 02.04.19 01:36 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketClient;

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
    private Socket socketOfClient;
    private BufferedWriter os;
    private BufferedReader is;

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
//            System.err.println("I/O Fehler: " + tikketServerHost);
        }
    }

    public void firstSync() {

    }

    public void starten() {
        if (socketOfClient != null) {
            System.out.println("tikketClient ist verbunden");
            tikketClientGUI gui = new tikketClientGUI(this);
        } else {
            System.err.println("Keine Serververbindung");
        }
    }

    public void beenden() {
        if (socketOfClient != null) {
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
                System.out.println("ticketErstellen: " + responseLine);
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

    public String ticketAusgeben() {
        try {
            // In OutputStream schreiben, senden
            os.write("ticketAusgeben");
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                if (responseLine.contains("-->>OK")) {
                    return responseLine;
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
        return null;
    }

    //Prüft ob das gegebene Ticket gültig ist. Wenn ja wird true zurückgegeben.
    public boolean ticketPruefen(int scan_UUID) {
        try {
            // In OutputStream schreiben, senden
            os.write("ticketPruefen:" + scan_UUID);
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;

            while ((responseLine = is.readLine()) != null) {
                if (responseLine.equals("-->>TRUE")) {
                    return true;
                } else if (responseLine.equals("-->>FALSE")) {
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

    public boolean ticketEinlass(int UUID) {
        try {
            // In OutputStream schreiben, senden
            os.write("ticketEinlass:" + UUID);
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                if (responseLine.equals("-->>OK")) {
                    System.out.println("ticketEinlass: true");
                    return true;
                } else {
                    System.out.println("ticketEinlass: false");
                    return false;
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
        return false;
    }

    private void veranstalterErstellen() {
    }

    private void veranstalterAusgeben() {
    }

    private void veranstalterLoeschen() {
    }

    private void veranstaltungErstellen() {
    }

    public String veranstaltungAusgeben() {
        try {
            // In OutputStream schreiben, senden
            os.write("veranstaltungAusgeben");
            os.newLine();
            os.flush();

            // Aus InputStream lesen, empfangen
            String responseLine;

            while ((responseLine = is.readLine()) != null) {
                if (responseLine.contains("-->>OK")) {
                    return responseLine;
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
        return null;
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