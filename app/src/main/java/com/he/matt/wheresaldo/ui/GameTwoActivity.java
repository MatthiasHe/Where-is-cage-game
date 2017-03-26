package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
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
    private boolean blockTouch = false;
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

    // METHODE QUI PERMET DE DEFINIR LE COMPORTEMENT LORSQUE L'ON CLIQUE SUR UNE ZONE PRÉDÉFINIE
    @Override
    public void onClickableAreaTouched(Object item) {
        if (blockTouch == false) {
            if (initialTimer == 20) {
                Intent endGame = new Intent(this, EndGame.class);
                endGame.putExtra("Chronomod", "Vous avez gagné en atteignant le dernier pallier !");
                startActivity(endGame);
            } else if (item instanceof Area) {
                clickableAreas = new ArrayList<>();
                setImage(nextImage);
                initialTimer = initialTimer - 20;
                Log.d("Timer", "InitialTimer : " + Float.toString(initialTimer));
                timer = initialTimer;
            } else if (item instanceof ChronoArea) {
                BlockScreen();
            }
        }

    }

    // METHODE QUI PERMET D'INITIALISER LES ZONES CLIQUABLES
    private void initializeClickableArea(ImageView image, int randomNumber) {

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), this);

        if (randomNumber == 1) {
            clickableAreas.add(new ClickableArea(550, 585, 150, 150, new Area("Cage 1")));
        } else {
            clickableAreas.add(new ClickableArea(500, 100, 150, 150, new Area("Cage 2")));
        }

        clickableAreas.add(new ClickableArea(1, 1, 1600, 1600, new ChronoArea("ChronoArea")));

        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    // METHODE QUI INITIALISE SIMPLEMENT NOTRE CHRONOMETRE
    private void InitiateChronometer() {
        new CountDownTimer(999999999, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = timer - 1;
                chronometer.setText("Seconds remaining: " + timer);
            }

            public void onFinish() {
            }
        }.start();
    }

    // METHODE QUI DEFINIT LA PROCHAINE IMAGE ALEATOIREMENT
    private void setImage(int imageNumber) {

        Random randomGenerator = new Random();

        if (nextImage == 1) {
            image.setImageResource(R.drawable.cage1);
            initializeClickableArea(image, imageNumber);
        } else {
            image.setImageResource(R.drawable.cage2);
            initializeClickableArea(image, imageNumber);
        }
        nextImage = randomGenerator.nextInt(2);
    }

    // METHOD QUI GERE LE BLOQUAGE DE L'ECRAN EN CAS DE MAUVAIS TOUCH
    private void BlockScreen() {

        blockTouch = true;

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                blockTouch = false;
            }
        }.start();
    }
}