package com.example.android.courtcounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mScoreViewA = findViewById(R.id.team_a_score);
        this.mScoreViewB = findViewById(R.id.team_b_score);

        if (savedInstanceState != null) { // maintain state if rotate device
            mActivityState = savedInstanceState.getParcelable(ACTIVITY_STATE);
        }
        else{
            mActivityState = new ActivityState(null);
        }
        updateUI();
    }

    private void updateUI() {
        mScoreViewA.setText(String.valueOf(this.mActivityState.mScoreTeamA));
        mScoreViewB.setText(String.valueOf(this.mActivityState.mScoreTeamB));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // save state of app for rotate
        outState.putParcelable(ACTIVITY_STATE, this.mActivityState);
        super.onSaveInstanceState(outState);
    }

    public void addThreeForTeamA(View view) {
        this.mActivityState.mScoreTeamA += 3;
        updateUI();
    }

    public void addTwoForTeamA(View view) {
        this.mActivityState.mScoreTeamA += 2;
        updateUI();
    }

    public void addOneForTeamA(View view) {
        this.mActivityState.mScoreTeamA += 1;
        updateUI();
    }

    public void addThreeForTeamB(View view) {
        this.mActivityState.mScoreTeamB += 3;
        updateUI();
    }

    public void addTwoForTeamB(View view) {
        this.mActivityState.mScoreTeamB += 2;
        updateUI();
    }

    public void addOneForTeamB(View view) {
        this.mActivityState.mScoreTeamB += 1;
        updateUI();
    }

    public void resetScore(View view) {
        this.mActivityState.mScoreTeamA = 0;
        this.mActivityState.mScoreTeamB = 0;
        updateUI();
    }

    @SuppressLint("ParcelCreator")
    private class ActivityState implements Serializable, Parcelable {
        private int mScoreTeamA = 0;
        private int mScoreTeamB = 0;

        ActivityState(Parcel in) {
            if (in != null) {
                this.mScoreTeamA = in.readInt();
                this.mScoreTeamB = in.readInt();
            }
        }

        @Override
        public int describeContents() {
            return Parcelable.CONTENTS_FILE_DESCRIPTOR;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mScoreTeamA);
            dest.writeInt(this.mScoreTeamB);
        }
    }

    private TextView mScoreViewA;
    private TextView mScoreViewB;

    private ActivityState mActivityState;

    private static final String ACTIVITY_STATE = "ACTIVITY_STATE";

}
