package com.tikket.tikketscan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick (View vie){
        IntentIntegrator scanDing = new IntentIntegrator(this);
        scanDing.initiateScan();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String barcode;
            barcode = scanResult.getContents();
            EditText ausgabeTicketnummer = (EditText) findViewById(R.id.ausgabeTicketnummer);
            ausgabeTicketnummer.setText(barcode);
        }
    }
}
