package com.example.stopwatch;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button pause_btn;
    Button play_btn;
    Button reset_btn;
    private int MilliSeconds = 0;
    public boolean isrunning = false;



    //    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pause_btn =  this.<Button>findViewById(R.id.pause_btn);
        play_btn = this.<Button>findViewById(R.id.play_btn);
        reset_btn =  this.<Button>findViewById(R.id.reset_btn);

        timer = findViewById(R.id.timer);
        startTimer();
        play_btn.setOnClickListener(v -> {
            isrunning = true;

        });
        pause_btn.setOnClickListener(v -> {
            isrunning = false;
        });
        reset_btn.setOnClickListener(v ->{
            MilliSeconds = 0;
            isrunning = false;
        });


    }

    public void startTimer() {
        Handler handler = new Handler();
         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 int mili = MilliSeconds%100;
                 int sec = (MilliSeconds/60)%60;
                 int min = MilliSeconds/3600;
                 if (isrunning){
                     MilliSeconds+=1;
                 }
                 String formatString = String.format(Locale.getDefault(),"%02d:%02d:%02d",min,sec,mili);
                 timer.setText(formatString);
                 handler.postDelayed(this,10);
             }
         };
         handler.post(runnable);
    }
}
