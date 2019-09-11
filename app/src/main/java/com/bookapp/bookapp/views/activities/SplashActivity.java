package com.bookapp.bookapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bookapp.bookapp.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView welcomeIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcomeIV = findViewById(R.id.welcome_image);

        Animation y_transition = AnimationUtils.loadAnimation(this, R.anim.open_anim);
        welcomeIV.startAnimation(y_transition);


        final Intent intent = new Intent(this, LoginActivity.class);


        Thread timer = new Thread() {

            @Override
            public void run() {

                try {
                    sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();
    }

}
