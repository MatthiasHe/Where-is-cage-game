package com.he.matt.wheresaldo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.he.matt.wheresaldo.R;

public class EndGame extends AppCompatActivity {

    private int score;
    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Bundle extras = getIntent().getExtras();
        score = extras.getInt("Score");

        scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText(String.valueOf(score));


/*        Bundle data = getIntent().getExtras();
        if (data != null) {
            score = data.getInt("Score");

            scoreView = (TextView) findViewById(R.id.score);
            scoreView.setText(score);
        }*/
    }
}
