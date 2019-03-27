package com.tikket.tikketscan;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketAddress;

public class ClientAsync extends AsyncTask<String, Integer, String> {
    private String tikketServerHost;
    private int tikketServerPort;
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    @Override
    protected String doInBackground(String... strings) {
        Log.d("myTag", "wenigstens in doBackground");

        //VA, die der Client bedient
        int SrvVa_ID;
        String SrvVa_name;
        //Serverstuff

        tikketServerHost = "192.168.178.1";
        tikketServerPort = 2001;
        try {
            Log.d("myTag", "haaaeeee");
            socketOfClient = new Socket(tikketServerHost, tikketServerPort);
            Log.d("myTag", "socket: ");
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
        return  "yay";
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
}
