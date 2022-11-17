package com.primera.donaya;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class teleton extends AppCompatActivity {
    WebView teleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleton);
        teleton = findViewById(R.id.wvteleton);
        teleton.loadUrl("https://lk.wompi.sv/eDk8");
    }
}