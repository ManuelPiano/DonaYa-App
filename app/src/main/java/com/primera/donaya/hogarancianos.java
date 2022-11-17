package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class hogarancianos extends AppCompatActivity {
    WebView hogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hogarancianos);
        hogar = findViewById(R.id.wvhogar);
        hogar.loadUrl("https://lk.wompi.sv/Y7gs");
    }
}