package com.he.matt.wheresaldo.ui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.he.matt.wheresaldo.R;

public class GameOneActivity extends AppCompatActivity {

    private TextView countDown;
    private ImageView image;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        countDown = (TextView) findViewById(R.id.countDown);
        image = (ImageView) findViewById(R.id.imageView2);
        image.setImageResource(R.drawable.cage1);

        mAttacher = new PhotoViewAttacher(image);

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