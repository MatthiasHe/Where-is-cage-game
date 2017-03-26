package com.he.matt.wheresaldo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.he.matt.wheresaldo.ui.GameOneActivity;
import com.he.matt.wheresaldo.ui.GameTwoActivity;

public class MainActivity extends AppCompatActivity {

    private Button uiButtonOne;
    private Button uiButtonTwo;
    private Button uiHallOfFame;
    private TextView uiTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiButtonOne = (Button)findViewById(R.id.button1);
        uiTextView = (TextView)findViewById(R.id.textView);
    }

    public void modeOneLaunch(View button) {
        Intent launchModeOne = new Intent(this, GameOneActivity.class);
        startActivity(launchModeOne);
    };

    public void modeTwoLaunch(View button) {
        Intent launchModeTwo = new Intent(this, GameTwoActivity.class);
        startActivity(launchModeTwo);
    };

    public void modeThreeLaunch(View button) {
        Intent launchModeTwo = new Intent(this, GameOneActivity.class);
        startActivity(launchModeTwo);
    };

    public void hallOfFameLaunch(View button) {
        Intent launchHallOfFame = new Intent(this, GameOneActivity.class);
        startActivity(launchHallOfFame);
    };
}
