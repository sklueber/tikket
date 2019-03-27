package com.tikket.tikketscan;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNet extends AsyncTask<Void, Void, String> { //von Website, nur noch zum Vergleich da

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;

    ClientNet(String addr, int port, TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
    }

    @Override
    protected String doInBackground(Void... arg0) {
        Log.d("myTag", "wenigstens in doBackground");
        Socket socket = null;

        try {
            Log.d("myTag", "haaaeeee");
            socket = new Socket(dstAddress, dstPort);
            Log.d("myTag", "socket: ");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            /*
             * notice: inputStream.read() will block if no data return
             */
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("myTag", "UnknownHostException: " + e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("myTag","IOException: " + e.toString());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

}