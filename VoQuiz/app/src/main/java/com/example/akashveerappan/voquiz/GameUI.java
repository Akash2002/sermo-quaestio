package com.example.akashveerappan.voquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/*
This class is the base class for the Game. It initializes the game objects such as the
text fields, buttons, radio buttons etc. There are also necessary methods, abstract methods,
to be implemented by the child class.
 */

public abstract class GameUI extends AppCompatActivity implements ScoreDialogShowable{


    protected TextView questionTextView;
    protected TextView answerTextView;
    protected QuestionBank questionBank;
    protected RadioGroup radioGroup;
    protected ArrayList<String> questionList, answerList;
    protected UserScoreTracker userScoreTracker;
    protected LayoutInflater layoutInflater;
    protected View scoreAlertView;
    protected Button retryButton;
    protected Button quitButton;
    protected TextView scoreAlertText;
    protected RadioButton option1, option2, option3, option4;
    protected TextView rightOrWrongText;
    protected TextView scoreTextView;
    protected Context context;

    //Default constructor
    public GameUI() {

    }
    //Overloaded constructor
    public GameUI(Context context, TextView questionTextView, TextView answerTextView, QuestionBank questionBank, RadioGroup radioGroup, ArrayList<String> questionList, ArrayList<String> answerList, UserScoreTracker userScoreTracker, LayoutInflater layoutInflater, View scoreAlertView, Button retryButton, Button quitButton, TextView scoreAlertText, RadioButton option1, RadioButton option2, RadioButton option3, RadioButton option4) {
        this.questionTextView = questionTextView;
        this.answerTextView = answerTextView;
        this.questionBank = questionBank;
        this.radioGroup = radioGroup;
        this.questionList = questionList;
        this.answerList = answerList;
        this.userScoreTracker = userScoreTracker;
        this.layoutInflater = layoutInflater;
        this.scoreAlertView = scoreAlertView;
        this.retryButton = retryButton;
        this.quitButton = quitButton;
        this.scoreAlertText = scoreAlertText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.context = context;
    }

    //method to setup all the view components of the app by connecting them to the XML code
    public void setupViewComponents () {
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        radioGroup = findViewById(R.id.radioGroup);
        questionBank = new QuestionBank();
        userScoreTracker = new UserScoreTracker();
        layoutInflater = LayoutInflater.from(context);
        rightOrWrongText = findViewById(R.id.rightOrWrongText);

        scoreAlertView = layoutInflater.inflate(R.layout.score_alert_display, null);

        scoreAlertText = scoreAlertView.findViewById(R.id.scoreAlertTextView);
        quitButton = scoreAlertView.findViewById(R.id.quitButton);
        retryButton = scoreAlertView.findViewById(R.id.retryButton);
    }

    //Abstract methods for the child classes to implement
    public abstract void updateUI(); //to show the user the content (ie. Questions, and Answers)
    public abstract void performAfterSubmitClick (ButtonClickable buttonClickable); //what to do after the user clicks on the submit button; takes in a ButtonClickable interface as a parameter which has a another method to do the same thing
    public abstract void performAfterStopClick (ButtonClickable buttonClickable); //what to do after the user clicks on the stop button; takes in a ButtonClickable interface as a parameter which has a another method to do the same thing


    //------------------------------ GETTERS AND SETTERS -----------------------------------


    public TextView getQuestionTextView() {
        return questionTextView;
    }

    public void setQuestionTextView(TextView questionTextView) {
        this.questionTextView = questionTextView;
    }

    public TextView getAnswerTextView() {
        return answerTextView;
    }

    public void setAnswerTextView(TextView answerTextView) {
        this.answerTextView = answerTextView;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<String> questionList) {
        this.questionList = questionList;
    }

    public ArrayList<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<String> answerList) {
        this.answerList = answerList;
    }

    public UserScoreTracker getUserScoreTracker() {
        return userScoreTracker;
    }

    public void setUserScoreTracker(UserScoreTracker userScoreTracker) {
        this.userScoreTracker = userScoreTracker;
    }

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

    public TextView getRightOrWrongText() {
        return rightOrWrongText;
    }

    public void setRightOrWrongText(TextView rightOrWrongText) {
        this.rightOrWrongText = rightOrWrongText;
    }

    public TextView getScoreTextView() {
        return scoreTextView;
    }

    public void setScoreTextView(TextView scoreTextView) {
        this.scoreTextView = scoreTextView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
