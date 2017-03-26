package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class GameTwoActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private TextView chronometer;
    private ImageView image;
    private int nextImage = 1;
    private int initialTimer = 120;
    private int timer = 120;
    List<ClickableArea> clickableAreas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);

        chronometer = (TextView) findViewById(R.id.chronometer);
        image = (ImageView) findViewById(R.id.photoView);
        setImage(nextImage);

        InitiateChronometer();
    }

    @Override
    public void onClickableAreaTouched(Object item) {
        if (initialTimer == 20) {
            Intent endGame = new Intent(this, EndGame.class);
            endGame.putExtra("Chronomod", "Vous avez gagné !");
            startActivity(endGame);
        }
        if (item instanceof Area) {
            clickableAreas = new ArrayList<>();
            setImage(nextImage);
            initialTimer = initialTimer - 20;
            Log.d("Timer", "InitialTimer : " + Float.toString(initialTimer));
            timer = initialTimer;
        }
    }

    private void initializeClickableArea(ImageView image, int randomNumber){

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), this);

        if (randomNumber == 1) {
            clickableAreas.add(new ClickableArea(550, 585, 150, 150, new Area("Cage 1")));
        }
        else {
            clickableAreas.add(new ClickableArea(500, 100, 150, 150, new Area("Cage 2")));
        }

        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    private void InitiateChronometer() {
        new CountDownTimer(999999999, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = timer - 1;
                chronometer.setText("Seconds remaining: " + timer);
            }

            public void onFinish() {}
        }.start();
    }

    private void setImage(int imageNumber) {

        Random randomGenerator = new Random();

        if (nextImage == 1) {
            image.setImageResource(R.drawable.cage1);
            initializeClickableArea(image, imageNumber);
        }
        else {
            image.setImageResource(R.drawable.cage2);
            initializeClickableArea(image, imageNumber);
        }

        nextImage = randomGenerator.nextInt(2);
    }
}