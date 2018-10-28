package com.example.akashveerappan.voquiz;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
This is the class that shows, manages, and executes the unlimited game. This implements ButtonClickable to manage
the user button clicks, and extends GameUI, the main parent for this project.
 */

public class UnlimitedGame extends GameUI implements ButtonClickable, ScoreDialogShowable{

    private QuestionBank questionBank;
    private ArrayList<String> questionArrayList, answerArrayList;
    private String question, answer;

    //What must the app do on the creation of its interface?

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setup the layouts and the context for the parent GameUI class
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        setContext(this);

        //Setup the question bank object and its respective array lists
        questionBank = new QuestionBank();
        questionArrayList = questionBank.getQuestionArray();
        answerArrayList = questionBank.getAnswerArray();

        //setup the view components of the app and update the overridden function: updateUI
        setupViewComponents();
        updateUI();

        //call the two button management functions
        performAfterSubmitClick(this);
        performAfterStopClick(this);


    }

    //call the respective interface calls
    @Override
    public void performAfterSubmitClick(ButtonClickable buttonClickable) {
        buttonClickable.onSubmitButtonPress();
    }

    @Override
    public void performAfterStopClick(ButtonClickable buttonClickable) {
        buttonClickable.onStopButtonPress();
    }

    //show the user content
    public void updateUI () {
        question = questionArrayList.get(questionBank.returnForArrayRandom());
        questionTextView.setText("Define: " + question);
        scoreTextView.setText(String.valueOf(userScoreTracker.getUserScore()));

        int answerIndex = questionBank.returnIndexOfQuestion(question);
        int randomizedOptionSelector = (int) (Math.random() * 4);

        switch (randomizedOptionSelector) {
            case 0:
                option1.setText(answerArrayList.get(answerIndex));
                break;
            case 1:
                option2.setText(answerArrayList.get(answerIndex));
                break;
            case 2:
                option3.setText(answerArrayList.get(answerIndex));
                break;
            case 3:
                option4.setText(answerArrayList.get(answerIndex));
                break;
        }

        // Algorithm for presenting different choices to the user, but with only one correct choice
        int answerOptionIndexForUI = questionBank.returnForArrayRandom();

        //loop through until the random choice is retrieved
        while (answerOptionIndexForUI == answerIndex || answerOptionIndexForUI + 1 >= answerArrayList.size() || answerOptionIndexForUI - 1 < 0) {
            answerOptionIndexForUI = questionBank.returnForArrayRandom();
        }

        //set the text of the various options with the random values
        if (answerOptionIndexForUI != answerIndex) {
            if (randomizedOptionSelector == 0) {
                option2.setText(answerArrayList.get(answerOptionIndexForUI));
                option3.setText(answerArrayList.get(answerOptionIndexForUI + 1));
                option4.setText(answerArrayList.get(answerOptionIndexForUI - 1));
            } else if (randomizedOptionSelector == 1) {
                option1.setText(answerArrayList.get(answerOptionIndexForUI));
                option3.setText(answerArrayList.get(answerOptionIndexForUI + 1));
                option4.setText(answerArrayList.get(answerOptionIndexForUI - 1));
            } else if (randomizedOptionSelector == 2) {
                option1.setText(answerArrayList.get(answerOptionIndexForUI));
                option2.setText(answerArrayList.get(answerOptionIndexForUI + 1));
                option4.setText(answerArrayList.get(answerOptionIndexForUI - 1));
            } else if (randomizedOptionSelector == 3) {
                option1.setText(answerArrayList.get(answerOptionIndexForUI));
                option2.setText(answerArrayList.get(answerOptionIndexForUI + 1));
                option3.setText(answerArrayList.get(answerOptionIndexForUI - 1));
            }
        }
    }

    // restrict the user from escaping from the game with the hit of the back button.
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Click Stop to Finish.", Toast.LENGTH_SHORT).show();
    }

    // show the user score with an alert popup dialog
    public void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UnlimitedGame.this);
        builder.setView(scoreAlertView);
        scoreAlertText.setText(String.valueOf(userScoreTracker.getUserScore()));

        //if they quit, take them to the home page
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnlimitedGame.this, WelcomePage.class));
            }
        });

        //else if they want to retry, get them back to the game
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnlimitedGame.this, UnlimitedGame.class));
            }
        });

        builder.create().show();
        System.out.println("Dialog Shown");
    }

    //Check if the answer is correct, show the next question, show if it is correct or not, and manage the score when the user hits the submit button
    @Override
    public void onSubmitButtonPress() {
        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = findViewById(radioGroup.getCheckedRadioButtonId());
                if (rb != null) {
                    if (rb.getText().length() > 0) {
                        answer = rb.getText().toString();
                        if (questionBank.isAnswerCorrect(question, answer)) {
                            rightOrWrongText.setTextColor(Color.GREEN);
                            rightOrWrongText.setText("Correct!");
                            System.out.println("Correct");
                            userScoreTracker.incrementScore();
                        } else {
                            rightOrWrongText.setTextColor(Color.RED);
                            rightOrWrongText.setText("Incorrect¡");
                            userScoreTracker.decrementScore();
                        }
                        updateUI();
                    } else {
                        Toast.makeText(getApplicationContext(), "Choose an option!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Choose an option!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Show the score dialog for the user when they hit the stop button
    @Override
    public void onStopButtonPress() {
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScoreDialog();
            }
        });
    }
}
