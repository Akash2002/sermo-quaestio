package com.example.akashveerappan.voquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimedGame extends TimedGameUI implements ButtonClickable{

    //Initialization of variables

    private ArrayList<String> questionArrayList, answerArrayList;
    private String question, answer;

    private boolean timeNotFinished = true;
    private View scoreAlertView;
    private Button retryButton;
    private Button quitButton;
    private TextView scoreAlertText;
    private Integer seconds = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_game);

        //implement all game functions

        setupViewComponents();
        setupSpecificTimedGameViewComponents();
        performAfterSubmitClick(this);
        performAfterStopClick(this);
        manageCountDownTimer();
        updateUI();

    }

    //--------------------- Methods of this class and of the extended/implemented classes/interfaces ---------------------

    //Because of technical difficulties, this method has been separated from the setupViewComponents method of the TimedGameUI class
    public void setupSpecificTimedGameViewComponents() {
        scoreAlertView = layoutInflater.inflate(R.layout.score_alert_display, null);

        scoreAlertText = scoreAlertView.findViewById(R.id.scoreAlertTextView);
        quitButton = scoreAlertView.findViewById(R.id.quitButton);
        retryButton = scoreAlertView.findViewById(R.id.retryButton);

        questionArrayList = questionBank.getQuestionArray();
        answerArrayList = questionBank.getAnswerArray();
    }

    //implementation of the ScoreDialogShowable interface
    public void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TimedGame.this);
        builder.setView(scoreAlertView);
        scoreAlertText.setText(String.valueOf(userScoreTracker.getUserScore()));

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimedGame.this, WelcomePage.class));
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimedGame.this, TimedGame.class));
            }
        });

        builder.create().show();
        System.out.println("Dialog Shown");
    }

    //implementation of the updateUI method: sets the options and question for the user
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

        int answerOptionIndexForUI = questionBank.returnForArrayRandom();

        while (answerOptionIndexForUI == answerIndex || answerOptionIndexForUI + 1 >= answerArrayList.size() || answerOptionIndexForUI - 1 < 0) {
            answerOptionIndexForUI = questionBank.returnForArrayRandom();
        }

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

    //what to do after the user clicks the submit button
    @Override
    public void performAfterSubmitClick(ButtonClickable buttonClickable) {
        buttonClickable.onSubmitButtonPress();
    }

    //what to do after the user clicks the stop button
    @Override
    public void performAfterStopClick(ButtonClickable buttonClickable) {
        buttonClickable.onStopButtonPress();
    }

    //what to do if the user clicks the in-built back button
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Click Stop to finish, or complete the game!", Toast.LENGTH_SHORT).show();
    }

    //Helps implement the performSubmitClick: checks if the answer is right or wrong, manages score, and shows the result
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

    //Helps implement the perform stopButtonClick: stops timer, shows the score
    @Override
    public void onStopButtonPress() {
        findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCountDownTimer().cancel();
                showScoreDialog();
            }
        });
    }

    public RadioButton getOption1() {
        return option1;
    }

    public void setOption1(RadioButton option1) {
        this.option1 = option1;
    }

    public RadioButton getOption2() {
        return option2;
    }

    public void setOption2(RadioButton option2) {
        this.option2 = option2;
    }

    public RadioButton getOption3() {
        return option3;
    }

    public void setOption3(RadioButton option3) {
        this.option3 = option3;
    }

    public RadioButton getOption4() {
        return option4;
    }

    public void setOption4(RadioButton option4) {
        this.option4 = option4;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }


    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    public ArrayList<String> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<String> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public ArrayList<String> getAnswerArrayList() {
        return answerArrayList;
    }

    public void setAnswerArrayList(ArrayList<String> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserScoreTracker getUserScoreTracker() {
        return userScoreTracker;
    }

    public void setUserScoreTracker(UserScoreTracker userScoreTracker) {
        this.userScoreTracker = userScoreTracker;
    }

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

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public View getScoreAlertView() {
        return scoreAlertView;
    }

    public void setScoreAlertView(View scoreAlertView) {
        this.scoreAlertView = scoreAlertView;
    }

    public Button getRetryButton() {
        return retryButton;
    }

    public void setRetryButton(Button retryButton) {
        this.retryButton = retryButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    public TextView getScoreAlertText() {
        return scoreAlertText;
    }

    public void setScoreAlertText(TextView scoreAlertText) {
        this.scoreAlertText = scoreAlertText;
    }


}

