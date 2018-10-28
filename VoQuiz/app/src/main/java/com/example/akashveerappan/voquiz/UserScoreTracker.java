package com.example.akashveerappan.voquiz;

/*
This user score tracker class makes it easy to track the score of the user
 */

public class UserScoreTracker {

    private int userScore; //the score of the user

    //default constructor
    public UserScoreTracker () {
        this.userScore = 0;
    }

    //method to increase score
    public void incrementScore () {
        userScore ++;
    }

    //method to decrease score
    public void decrementScore () {
        userScore --;
    }

    // --------------------------------------- GETTERS AND SETTERS ----------------------------------------

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
