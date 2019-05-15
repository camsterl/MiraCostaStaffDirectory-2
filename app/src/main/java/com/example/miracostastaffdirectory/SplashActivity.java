package com.example.miracostastaffdirectory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.victor.loading.rotate.RotateLoading;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Activity thisAct = this;

        RotateLoading rotateLoading = findViewById(R.id.rotateloading);
        if(!rotateLoading.isStart())
            rotateLoading.start();


        TimerTask task = new TimerTask() {
            @Override
            public void run() {



                thisAct.finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,1500);
    }
}

