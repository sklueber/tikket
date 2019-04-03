package com.tikket.tikketscan;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAsync {

    private String tikketServerHost;
    private int tikketServerPort;
    private boolean verbunden;
    Socket socketOfClient;
    BufferedWriter os;
    BufferedReader is;

    ClientAsync(String hostIP, int port, MainActivity a) {
        verbunden = false;
        tikketServerHost = hostIP;
        tikketServerPort = port;
        AsyncVerbinden taskVerbinden = new AsyncVerbinden(a);
        taskVerbinden.execute("");
    }

    public void asyncTicketPreuefen(int pTktNr, MainActivity a) {
        AsyncTikketPruefen pruef = new AsyncTikketPruefen(a);
        pruef.execute(pTktNr);
    }

    public void asyncTicketEinlassen(int pTktNr, MainActivity a){
        AsyncTikketEinlassen einlass = new AsyncTikketEinlassen(a);
        einlass.execute(pTktNr);
    }

    public boolean istVerbunden() {
        return verbunden;
    }

    class AsyncVerbinden extends AsyncTask<String, Integer, String> { //Async verlagert Netzwerkaufgaben in Nebenthreads um UI nicht zu stören, Pflicht bei Android
        //Erklaerung der Variablen: https://stackoverflow.com/a/29559386

        public MainActivity aktivitaet;

        public AsyncVerbinden(MainActivity a) {
            this.aktivitaet = a;
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("myTag", "wenigstens in doBackground"); //Log.d() ~ System.out.println in Android

            Socket socketOfClient;
            String result;
            try {
                socketOfClient = new Socket(tikketServerHost, tikketServerPort);
                // Create output stream at the client (to send data to the server)
                os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

                // Input stream at Client (Receive data from the server).
                is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
                result = "Verbunden";
                verbunden = true;
            } catch (UnknownHostException e) {
                Log.d("myTag", "Unbekannter Host: " + tikketServerHost);
                result = "Fehler: unbekannter Host";
                verbunden = false;
            } catch (IOException e) {
                Log.d("myTag", "I/O Fehler: " + tikketServerHost);
                Log.d("myTag", e.getStackTrace()[0].toString());
                result = "Fehler";
                verbunden = false;
            }
            return result; //benutzen wir nicht, wird aber von AsyncTask gefordert
        }

        @Override
        protected void onPostExecute(String s) {
            aktivitaet.setzeTktNrTxt(s);
        }
    }


    class AsyncTikketPruefen extends AsyncTask<Integer, String, Boolean> { //Prüft ob das gegebene Ticket gültig ist. Wenn ja wird true zurückgegeben.

        MainActivity aktivitaet;
        int tktNr;

        public AsyncTikketPruefen(MainActivity a) {
            this.aktivitaet = a;
        }

        @Override
        protected Boolean doInBackground(Integer... pUUID) { //Async verlagert Netzwerkaufgaben in Nebenthreads um UI nicht zu stören, Pflicht bei Android
            tktNr = pUUID[0];
            try {
                // In OutputStream schreiben, senden
                os.write("ticketPruefen:" + tktNr);
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

        protected void onPostExecute(Boolean result) { //erhält das Ergebnis von doInBackground nachdem es vorliegt, läuft im UI-Thread
            Log.d("ClientAsync", "es ist in der Post!");
            aktivitaet.tktGueltigAnzeige(result);
            asyncTicketEinlassen(tktNr, aktivitaet);
        }
    }

    class AsyncTikketEinlassen extends AsyncTask<Integer, String, Boolean> {
        MainActivity aktivitaet;
        public AsyncTikketEinlassen(MainActivity a){
            this.aktivitaet = a;
        }
        @Override
        protected Boolean doInBackground(Integer... pUUID) {
            try {
                // In OutputStream schreiben, senden
                os.write("ticketEinlass:" + pUUID[0]);
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
        protected void onPostExecute(Boolean result) { //erhält das Ergebnis von doInBackground nachdem es vorliegt, läuft im UI-Thread
            Log.d("ClientAsync", "es ist in der Post!");
            aktivitaet.tktEingelassenAnzeige(result);
        }
    }
}

 /*class AsyncTktErstellen extends AsyncTask<Void, Boolean, Boolean> { //aus Gruenden der Funktionstrennung nicht verwendet

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
    }*/

