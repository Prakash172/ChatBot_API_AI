package com.erprakash.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private TextView timeView;
    private int seconds;
    private int hours;
    private int minutes;
    boolean running = false;
    boolean wasRunning ;
//    private Button start, stop, reset ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        timeView = findViewById(R.id.timeView);
//        start = findViewById(R.id.start);
//        stop = findViewById(R.id.stop);
//        reset = findViewById(R.id.reset);
        if(savedInstanceState!= null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTime();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInsatnceState) {
        super.onSaveInstanceState(savedInsatnceState);
        savedInsatnceState.putInt("seconds", seconds);
        savedInsatnceState.putBoolean("running", running);
        savedInsatnceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    protected void onStop(){
        super.onStop();
        wasRunning = running ;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasRunning)
            running = true;
    }

    public void onStartClick(View view) {
        running = true;
    }

    public void onStopClick(View view) {
        running = false ;
    }

    public void onResetClick(View view) {
        running = false;
        seconds = 0;
    }

    public void runTime(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                hours = seconds / 3600;
                minutes = (seconds%3600)/60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,seconds%60);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });


    }
}
