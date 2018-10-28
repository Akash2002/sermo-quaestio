package com.example.akashveerappan.voquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Time;
import java.util.Timer;

/*
This is the activity class which the app shows to the user when it is opened
 */

public class WelcomePage extends AppCompatActivity {

    private ConstraintLayout timedButton, unlimitedButton, viewListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        timedButton = findViewById(R.id.timedModeButton);
        unlimitedButton = findViewById(R.id.unlimitedButton);
        viewListButton = findViewById(R.id.viewListButton);

        // The three options for the user: Learn the words, play timed or infinity versions of the game and
        // navigate the user to that page.

        timedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this, TimedGame.class));
            }
        });
        unlimitedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this, UnlimitedGame.class));
            }
        });
        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this, LearnVocabActivity.class));
            }
        });

    }
}
