package com.he.matt.wheresaldo.ui;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.he.matt.wheresaldo.Area;
import com.he.matt.wheresaldo.R;

import java.util.ArrayList;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

public class GameOneActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private TextView countDown;
    private ImageView image;
    List<ClickableArea> clickableAreas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);

        countDown = (TextView) findViewById(R.id.countDown);
        image = (ImageView) findViewById(R.id.photoView);
        image.setImageResource(R.drawable.cage2);

        initializeClickableArea(image);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                countDown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDown.setText("done!");
            }
        }.start();
    }

    @Override
    public void onClickableAreaTouched(Object item) {
        if (item instanceof Area) {
            String text = ((Area) item).getName();
            Intent endGame = new Intent(this, EndGame.class);
            startActivity(endGame);
        }
    }

    private void initializeClickableArea(ImageView image){

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(image), this);

        if ("c" == "cage1") {
            clickableAreas.add(new ClickableArea(625, 660, 150, 150, new Area("Cage 1")));
        }
        else {
            clickableAreas.add(new ClickableArea(577, 175, 150, 150, new Area("Cage 2")));
        }

        clickableAreasImage.setClickableAreas(clickableAreas);
    }
}