package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class caritas extends AppCompatActivity {
    WebView  wvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caritas);
        wvc = findViewById(R.id.caritaswv);
        wvc.loadUrl("https://lk.wompi.sv/XGoe");
    }
}