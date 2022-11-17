package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class cruzroja extends AppCompatActivity {
    WebView cruzroja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruzroja);

        cruzroja = findViewById(R.id.wvcruzroja);
        cruzroja.loadUrl("https://lk.wompi.sv/m9zF");
    }
}