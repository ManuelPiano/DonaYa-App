package com.primera.donaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Mainlogotipo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        //animaciones
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
      //  TextView textView2 = findViewById(R.id.textView2);
       // ImageView imageView2 = findViewById(R.id.imageView2);
       // textView2.setAnimation(animation2);
       // imageView2.setAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Mainlogotipo.this,MainActivity.class);
                        startActivity(intent);
                finish();
            }
        }, 4000);

        setContentView(R.layout.activity_mainlogotipo);
    }
}