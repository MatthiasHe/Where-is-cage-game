package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.he.matt.wheresaldo.Area;
import com.he.matt.wheresaldo.ChronoArea;
import com.he.matt.wheresaldo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

public class GameOneActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private TextView chronometer;
    private ImageView image;
    private int timer = 0;
    List<ClickableArea> clickableAreas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        chronometer = (TextView) findViewById(R.id.chronometer);
        image = setImage();

        chronometer.setText("Seconds remaining : 0");

        new CountDownTimer(999999999, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = timer + 1;
                chronometer.setText("seconds remaining: " + timer);
            }

            public void onFinish() {}
        }.start();
    }

    @Override
    public void onClickableAreaTouched(Object item) {
        if (item instanceof Area ) {
            Intent endGame = new Intent(this, EndGame.class);
            startActivity(endGame);
        }
        if (item instanceof ChronoArea) {
            timer = timer + 2;
        }
    }

    private void initializeClickableArea(ImageView image, int randomNumber){

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), this);

        if (randomNumber == 1) {
            clickableAreas.add(new ClickableArea(625, 660, 200, 200, new Area("Cage 1")));
        }
        else {
            clickableAreas.add(new ClickableArea(577, 175, 200, 200, new Area("Cage 2")));
        }

        clickableAreas.add(new ClickableArea(1, 1, 1200, 1200, new ChronoArea("ChronoArea")));

        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    private ImageView setImage(){
        ImageView image;
        Random randomGenerator = new Random();

        image = (ImageView) findViewById(R.id.photoView);
        int randomNumber = randomGenerator.nextInt(2);

        if (randomNumber == 1) {
            image.setImageResource(R.drawable.cage1);
            initializeClickableArea(image, randomNumber);
        }
        else {
            image.setImageResource(R.drawable.cage2);
            initializeClickableArea(image, randomNumber);
        }

        return image;
    }
}