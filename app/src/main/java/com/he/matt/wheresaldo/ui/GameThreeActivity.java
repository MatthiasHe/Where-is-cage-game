package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.he.matt.wheresaldo.Area;
import com.he.matt.wheresaldo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

public class GameThreeActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private TextView chronometer;
    private ImageView image;
    private int nextImage = 1;
    private int timer = 10;
    private int cageFound = 0;
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
        if (item instanceof Area) {
            clickableAreas = new ArrayList<>();
            setImage(nextImage);
            cageFound++;
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
        new CountDownTimer(timer * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = timer - 1;
                chronometer.setText("Seconds remaining: " + timer);
            }

            public void onFinish() {
                endGame();
            }
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

    private void endGame() {
        Intent endGame = new Intent(this, EndGame.class);
        endGame.putExtra("CageFound", cageFound);
        startActivity(endGame);
    }
}
