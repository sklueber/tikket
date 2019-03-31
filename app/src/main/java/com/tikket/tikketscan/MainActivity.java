package com.tikket.tikketscan;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ClientAsync client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starteGUI();

    }
    public void scanOnClick (View vie){ //Scan-Button wird gedrückt
        IntentIntegrator scanDing = new IntentIntegrator(this);
        scanDing.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //Ergebnis des Barcode-Aufrufs wird verarbeitet
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            TextView tktNr = findViewById(R.id.textViewTktNr);
            if(result.getContents() == null) {
                tktNr.setText(R.string.cancelled);
                codePruefen("ERROR"); //codePruefen erhält ERROR, um die Statusanzeige entsprechend zu färben
            } else {
                tktNr.setText(result.getContents());
                codePruefen(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void codePruefen(String pTicketnummer) { //Das Ergebnis des Barcode-Aufrufs wird hier nach Gültigkeit überprüft
        TextView status = findViewById(R.id.textViewStatus);
        GradientDrawable drawable = (GradientDrawable) status.getBackground(); //"Status"-Anzeige der GUI
        int ticketnummer;
        try {  //ob das gescannte Ergebnis überhaupt aus Zahlen besteht
            ticketnummer = Integer.parseInt(pTicketnummer);
        } catch (NumberFormatException e) { //gescannter Code war keine Zahl oder Scan wurde abgebrochen
            drawable.setColor(ContextCompat.getColor(this, R.color.colorCancelYellow));
            status.setText(R.string.cancelled);
            return;
        }
        if (client.verbunden()) {
            client.asyncTicketPreuefen(ticketnummer, this); //Verbindung zum Server über AsyncTasks, wenn der Code eine gültige Zahl war
        } else {
            drawable.setColor(ContextCompat.getColor(this, R.color.colorCancelYellow));
            status.setText(R.string.disconnected);
        }
    }

    public void clientOnClick(View view) { //Button Client Starten
        EditText ip = (EditText)findViewById(R.id.editTextIP);
        client = new ClientAsync(ip.getText().toString(), 2001, this);
    }

    public void testOnClick(View view) { //Button Test
       codePruefen("770331234");
    }

    public void tktGueltigAnzeige(boolean gueltig){ //wird in ClientAsync aufgerufen und stellt in GUI dar, ob das Ticket gueltig ist
        TextView status = findViewById(R.id.textViewStatus);
        GradientDrawable drawable = (GradientDrawable) status.getBackground(); //"Status"-Anzeige der GUI
        if(gueltig){
            drawable.setColor(ContextCompat.getColor(this, R.color.colorValidGreen));
            status.setText(R.string.valid); //TODO schöne Sounds
        } else {
            drawable.setColor(ContextCompat.getColor(this, R.color.colorInvalidRed));
            status.setText(R.string.invalid);
            final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
            tg.startTone(ToneGenerator.TONE_PROP_BEEP2); //vlt durch was cooleres ersetzen
        }
    }

    private void starteGUI(){ //abgerundete Ecken!
        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.textViewStatus);
        status.setBackgroundResource(R.drawable.rounded_corner);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();
        drawable.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    public void setzeTktNrTxt(String s){
        TextView tktNr = findViewById(R.id.textViewTktNr);
        tktNr.setText(s);
    }

    public void ipOnClick(View view){
        EditText ip = findViewById(R.id.editTextIP);
        if(ip.getVisibility() == View.INVISIBLE){
            ip.setVisibility(View.VISIBLE);
        } else if(ip.getVisibility()==View.VISIBLE){
            ip.setVisibility(View.INVISIBLE);
        }
    }

}