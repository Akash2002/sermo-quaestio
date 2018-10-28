package com.example.akashveerappan.voquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//This is the class that implements the Recycler View built in another class

public class LearnVocabActivity extends AppCompatActivity {

    //declare variables of the recycler view and the question bank to get the array lists
    private RecyclerView recyclerView;
    private QuestionBank questionBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_vocab);

        //setup and initialize
        recyclerView = findViewById(R.id.latinRecyclerView);
        questionBank = new QuestionBank();

        //Implement the recycler view
        VocabRecyclerAdapter vocabRecyclerAdapter = new VocabRecyclerAdapter(questionBank.getQuestionArray(), questionBank.getAnswerArray());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(vocabRecyclerAdapter);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }
}
