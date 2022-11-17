package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class isna extends AppCompatActivity {
    WebView isna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isna);

        isna = findViewById(R.id.wvisna);
        isna.loadUrl("https://lk.wompi.sv/VHC4");
    }
}