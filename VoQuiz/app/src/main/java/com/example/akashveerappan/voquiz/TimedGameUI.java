package com.example.akashveerappan.voquiz;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/*
This class is a modified child class of the parent class GameUI. While the GameUI is the class for classic games,
this class provides timer based capabilities. This, therefore, includes more user components and variables to manage
the timer.
 */

public abstract class TimedGameUI extends GameUI {

    protected boolean timeNotFinished = true;
    protected ProgressBar progressBar;
    protected LayoutInflater layoutInflater;
    protected View scoreAlertView;
    protected Button retryButton;
    protected Button quitButton;
    protected TextView scoreAlertText;
    protected Integer seconds = 30000;
    private CountDownTimer countDownTimer; //the countDownTimer is the object that will take care of counting down

    //default constructor
    public TimedGameUI () {

    }

    //overloaded constructor
    public TimedGameUI(Context context, TextView questionTextView, TextView answerTextView, QuestionBank questionBank, RadioGroup radioGroup, ArrayList<String> questionList, ArrayList<String> answerList, UserScoreTracker userScoreTracker, LayoutInflater layoutInflater, View scoreAlertView, Button retryButton, Button quitButton, TextView scoreAlertText, RadioButton option1, RadioButton option2, RadioButton option3, RadioButton option4, boolean timeNotFinished, ProgressBar progressBar, LayoutInflater layoutInflater1, View scoreAlertView1, Button retryButton1, Button quitButton1, TextView scoreAlertText1, Integer seconds) {
        super(context, questionTextView, answerTextView, questionBank, radioGroup, questionList, answerList, userScoreTracker, layoutInflater, scoreAlertView, retryButton, quitButton, scoreAlertText, option1, option2, option3, option4);
        this.timeNotFinished = timeNotFinished;
        this.progressBar = progressBar;
        this.layoutInflater = layoutInflater1;
        this.scoreAlertView = scoreAlertView1;
        this.retryButton = retryButton1;
        this.quitButton = quitButton1;
        this.scoreAlertText = scoreAlertText1;
        this.seconds = seconds;
    }

    @Override
    public void setupViewComponents() {
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        radioGroup = findViewById(R.id.radioGroup);
        questionBank = new QuestionBank();
        userScoreTracker = new UserScoreTracker();
        progressBar = findViewById(R.id.progressBar);
        rightOrWrongText = findViewById(R.id.rightOrWrongText);
        layoutInflater = LayoutInflater.from(this);
    }

    //method to manage the countDownTimer
    public void manageCountDownTimer() {
        //CountDownTimer is an interface that has two methods: onTick and onFinish.
        //onTick happens every how many ever milliseconds mentioned in the countDownInterval
        //parameter, while onFinish is after the timer is done.
        countDownTimer = new CountDownTimer(seconds, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(Math.round(seconds - millisUntilFinished)); //show the progress bar to the user with the active progress
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(progressBar.getMax()); //finish the progress
                showScoreDialog(); //show the score dialog to the user
            }
        };
        countDownTimer.start(); //start the timer
    }

// -------------------------------- GETTERS AND SETTERS -----------------------------

    public boolean isTimeNotFinished() {
        return timeNotFinished;
    }

    public void setTimeNotFinished(boolean timeNotFinished) {
        this.timeNotFinished = timeNotFinished;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    @Override
    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public View getScoreAlertView() {
        return scoreAlertView;
    }

    @Override
    public void setScoreAlertView(View scoreAlertView) {
        this.scoreAlertView = scoreAlertView;
    }

    @Override
    public Button getRetryButton() {
        return retryButton;
    }

    @Override
    public void setRetryButton(Button retryButton) {
        this.retryButton = retryButton;
    }

    @Override
    public Button getQuitButton() {
        return quitButton;
    }

    @Override
    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    @Override
    public TextView getScoreAlertText() {
        return scoreAlertText;
    }

    @Override
    public void setScoreAlertText(TextView scoreAlertText) {
        this.scoreAlertText = scoreAlertText;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }
}
