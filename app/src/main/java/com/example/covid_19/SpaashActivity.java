package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SpaashActivity extends AppCompatActivity {
    int pros;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();

                    Intent intent=new Intent(SpaashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        });

        thread.start();
    }
    public void dowork()
    {
        for (pros=10;pros<=100;pros=pros+30){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
