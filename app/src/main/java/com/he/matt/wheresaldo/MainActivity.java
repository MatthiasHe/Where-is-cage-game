package com.he.matt.wheresaldo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        uiTextView.setText("ça fonctionne");
    };

    public void modeTwoLaunch(View button) {

    };

    public void hallOfFameLaunch(View button) {

    };
}
