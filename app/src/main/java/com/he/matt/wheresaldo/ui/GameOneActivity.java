package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private int finalTimer;
    private int timer = 0;
    List<ClickableArea> clickableAreas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        chronometer = (TextView) findViewById(R.id.chronometer);
        image = setImage();

        InitiateChronometer();

        chronometer.setText("Chronomoter : 0");
    }

    // METHODE QUI PERMET DE DEFINIR LE COMPORTEMENT LORSQUE L'ON CLIQUE SUR UNE ZONE PRÉDÉFINIE
    @Override
    public void onClickableAreaTouched(Object item) {
        if (item instanceof Area ) {
            finalTimer = timer;
            Intent endGame = new Intent(this, EndGame.class);
            endGame.putExtra("Score", finalTimer);
            startActivity(endGame);
        } else if (item instanceof ChronoArea) {
            timer = timer + 2;
        }
    }

    // METHODE QUI PERMET D'INITIALISER LES ZONES CLIQUABLES
    private void initializeClickableArea(ImageView image, int randomNumber){

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), this);

        if (randomNumber == 1) {
            clickableAreas.add(new ClickableArea(550, 585, 150, 150, new Area("Cage 1")));
        }
        else {
            clickableAreas.add(new ClickableArea(500, 100, 150, 150, new Area("Cage 2")));
        }

        clickableAreas.add(new ClickableArea(1, 1, 1600, 1600, new ChronoArea("ChronoArea")));

        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    // METHODE QUI DEFINIT UNE IMAGE ALEATOIREMENT
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

    // METHODE QUI INITIALISE SIMPLEMENT NOTRE CHRONOMETRE
    private void InitiateChronometer() {
        new CountDownTimer(999999999, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = timer + 1;
                chronometer.setText("seconds remaining: " + timer);
            }

            public void onFinish() {}
        }.start();
    }
}