package com.tikket.tikketscan;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAsync{

    private String tikketServerHost;
    private int tikketServerPort;
    Socket socketOfClient;
    BufferedWriter os;
    BufferedReader is;

    ClientAsync(String hostIP, int port) {
        tikketServerHost = hostIP;
        tikketServerPort = port;
        AsyncVerbinden taskVerbinden = new AsyncVerbinden();
        taskVerbinden.execute("");
    }

    public void asyncTicketPreuefen(int pTktNr){
        AsyncTikketPruefen pruef = new AsyncTikketPruefen();
        pruef.execute(pTktNr);
    }

    class AsyncVerbinden extends AsyncTask<String, Integer, String> { //Async verlagert Netzwerkaufgaben in Nebenthreads um UI nicht zu stören, Pflicht bei Android
//Erklaerung der Variablen: https://stackoverflow.com/a/29559386

        @Override
        protected String doInBackground(String... strings) {
            Log.d("myTag", "wenigstens in doBackground"); //Log.d() ~ System.out.println in Android

            Socket socketOfClient;

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
            return ""; //benutzen wir nicht, wird aber von AsyncTask gefordert
        }
    }


    class AsyncTikketPruefen extends AsyncTask<Integer, String, Boolean> { //Prüft ob das gegebene Ticket gültig ist. Wenn ja wird true zurückgegeben.

        public AsyncTikketPruefen() {
        }

        @Override
        protected Boolean doInBackground(Integer... pUUID) {
            try {
                // In OutputStream schreiben, senden
                os.write("ticketPruefen:" + pUUID[0]);
                os.newLine();
                os.flush();

                // Aus InputStream lesen, empfangen
                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    Log.d("myTag", "Server: " + responseLine);
                    if (responseLine.equals("-->>TRUE")) {
                        return true;
                    } else if (responseLine.equals("-->>FALSE")) {
                        return false;
                    }
                }
             //   os.close();
             //   is.close();
             //   socketOfClient.close();
            } catch (UnknownHostException e) {
                Log.d("myTag", "Unbekannter Host: " + e);
                return false;
            } catch (IOException e) {
                Log.d("myTag", "I/O Fehler:  " + e);
                return false;
            }
            return false;

        }

        protected void onPostExecute(Boolean result) { //erhält das Ergebnis von doInBackground nachdem es vorliegt
            Log.d("ClientAsync", "es ist in der Post!");
            if (result) {
        MainActivity.tktGueltig();
            } else {
               MainActivity.tktUngueltig();
            }

        }
    }


 class AsyncTktErstellen extends AsyncTask<Void, Boolean, Boolean> {

        public AsyncTktErstellen() {
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
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
                        return false;
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
            return false;
        }
    }
}

