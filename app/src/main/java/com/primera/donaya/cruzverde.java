package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class cruzverde extends AppCompatActivity {
    WebView cruzverde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruzverde);
        cruzverde = findViewById(R.id.wvcruzverde);
        cruzverde.loadUrl("https://lk.wompi.sv/QFQW");
    }
}