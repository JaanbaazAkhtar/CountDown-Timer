package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView CountDownText;
    private Button startPausebtn;
    private Button resetbtn;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountDownText = findViewById(R.id.timer_text);
        startPausebtn = findViewById(R.id.start_pause_button);
        resetbtn = findViewById(R.id.reset_button);

        startPausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
    }

    private void startTimer()
    {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                startPausebtn.setText("Start");
                startPausebtn.setVisibility(View.INVISIBLE);
                resetbtn.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        startPausebtn.setText("Pause");
        resetbtn.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int) mTimeLeftInMillis/1000/60;
        int seconds = (int) (mTimeLeftInMillis/1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes,seconds);
        CountDownText.setText(timeLeftFormatted);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        startPausebtn.setText("Start");
        resetbtn.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        resetbtn.setVisibility(View.INVISIBLE);
        startPausebtn.setVisibility(View.VISIBLE);
    }

}