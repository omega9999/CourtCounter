package com.example.android.courtcounter;

import java.io.Serializable;

public class ActivityState implements Serializable {

    public ActivityState() {
    }

    public int getScoreTeamA() {
        return mScoreTeamA;
    }

    public ActivityState setScoreTeamA(int scoreTeamA) {
        this.mScoreTeamA = scoreTeamA;
        return this;
    }

    public int getScoreTeamB() {
        return mScoreTeamB;
    }

    public ActivityState setScoreTeamB(int scoreTeamB) {
        this.mScoreTeamB = scoreTeamB;
        return this;
    }

    public ActivityState addScoreTeamA(int scoreTeamA) {
        this.mScoreTeamA += scoreTeamA;
        return this;
    }

    public ActivityState addScoreTeamB(int scoreTeamB) {
        this.mScoreTeamB += scoreTeamB;
        return this;
    }


    private int mScoreTeamA = 0;
    private int mScoreTeamB = 0;
}
