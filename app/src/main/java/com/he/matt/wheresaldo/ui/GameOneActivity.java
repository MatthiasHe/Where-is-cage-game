package com.he.matt.wheresaldo.ui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.he.matt.wheresaldo.R;

public class GameOneActivity extends AppCompatActivity {

    private TextView countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        countDown = (TextView) findViewById(R.id.countDown);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDown.setText("done!");
            }
        }.start();
    }
}