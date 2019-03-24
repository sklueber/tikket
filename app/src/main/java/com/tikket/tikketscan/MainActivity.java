package com.tikket.tikketscan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.textViewStatus);
        status.setBackgroundResource(R.drawable.rounded_corner);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();
        drawable.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }
    public void onClick (View vie){
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
                statusAnzeigen("ERROR");
            } else {
                tktNr.setText(result.getContents());
                statusAnzeigen(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /*public void statusAnzeigen(String pTicketnummer){
        TextView status = findViewById(R.id.textViewStatus);
    if(!android.text.TextUtils.isDigitsOnly(pTicketnummer)){ //ob das gescannte Ergebnis überhaupt aus Zahlen besteht
        status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCancelYellow));
        status.setText(R.string.cancelled);
    } else {
        if(pTicketnummer.equals("100")){
            status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorValidGreen));
            status.setText(R.string.valid);
        } else {
            status.setBackgroundColor(ContextCompat.getColor(this, R.color.colorInvalidRed));
            status.setText(R.string.invalid);
        }
    }
    }*/
    public void statusAnzeigen(String pTicketnummer){
        TextView status = findViewById(R.id.textViewStatus);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();
        if(!android.text.TextUtils.isDigitsOnly(pTicketnummer)){ //ob das gescannte Ergebnis überhaupt aus Zahlen besteht
            drawable.setColor(ContextCompat.getColor(this, R.color.colorCancelYellow));
            status.setText(R.string.cancelled);
        } else {
            if(pTicketnummer.equals("100")){
                drawable.setColor(ContextCompat.getColor(this, R.color.colorValidGreen));
                status.setText(R.string.valid); //TODO schöne Sounds
            } else {
                drawable.setColor(ContextCompat.getColor(this, R.color.colorInvalidRed));
                status.setText(R.string.invalid);
                final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
                tg.startTone(ToneGenerator.TONE_PROP_BEEP2); //vlt durch was cooleres ersetzen
            }
        }
    }
}
