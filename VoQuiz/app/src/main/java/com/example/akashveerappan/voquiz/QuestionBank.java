package com.example.akashveerappan.voquiz;

import java.util.ArrayList;
import java.util.Arrays;

/*
Class for managing the questions and answer. I could have created a class for holding the question and answer, but decided that array lists would make the code look less complicated as there are plenty of classes already
 */

public class QuestionBank{

    //Question and Answer data set for the game
    private ArrayList<String> questionArray = new ArrayList<>(Arrays.asList("est"," pater",
            "mater", "filius", "servus", "coquus", "canis", "tablino", "atrio", "horto",
            "via", "sedet", "bibit", "laborat", "dormit", "intrat", "circumspectat","cibus",
            "mensa", "salit","stat", "stertit"));

    private ArrayList<String> answerArray = new ArrayList<>(Arrays.asList("is", "father", "mother",
            "son", "slave", "cook", "dog", "study", "atrium", "garden", "street", "sit", "drink",
            "work", "sleep", "enter", "look around", "food", "table", "jumps", "stand", "bark"));

    public QuestionBank() {

    }

    public boolean isLengthEqual () {
        return questionArray.size() == answerArray.size();
    }

    //returns a random number for choosing a random question (word from the question array list)
    public int returnForArrayRandom () {
        if (this.isLengthEqual())
            return (int) (Math.random() * questionArray.size());
        return 0;
    }

    //returns the index of the question but is instead used for determining the answer as both the array lists have the same index values
    public int returnIndexOfQuestion (String question) {
        int index = 0;
        for (int i = 0; i < questionArray.size(); i++) {
            if (questionArray.get(i).equals(question)) {
                index = i;
            }
        }
        return index;
    }

    //determines if the answer is correct or wrong and returns a boolean
    public boolean isAnswerCorrect (String question, String answer) {
        int qIndex = questionArray.indexOf(question);
        int aIndex = answerArray.indexOf(answer);

        return qIndex == aIndex;
    }

    //--------------------------------- GETTERS AND SETTERS ---------------------------------

    public ArrayList<String> getQuestionArray() {
        return questionArray;
    }

    public void setQuestionArray(ArrayList<String> questionArray) {
        this.questionArray = questionArray;
    }

    public ArrayList<String> getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(ArrayList<String> answerArray) {
        this.answerArray = answerArray;
    }

}
