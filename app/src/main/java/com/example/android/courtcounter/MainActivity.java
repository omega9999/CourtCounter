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
            final Parcelable parcelable = savedInstanceState.getParcelable(ACTIVITY_STATE);
            if (parcelable instanceof WrapperParcellable){
                mActivityState = ((WrapperParcellable<ActivityState>)parcelable).getSerializable();
            }
        }
        else{
            mActivityState = new ActivityState();
        }
        updateUI();
    }

    private void updateUI() {
        mScoreViewA.setText(String.valueOf(this.mActivityState.getScoreTeamA()));
        mScoreViewB.setText(String.valueOf(this.mActivityState.getScoreTeamB()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // save state of app for rotate
        outState.putParcelable(ACTIVITY_STATE, new WrapperParcellable<>(this.mActivityState));
        super.onSaveInstanceState(outState);
    }

    public void addThreeForTeamA(View view) {
        this.mActivityState.addScoreTeamA(3);
        updateUI();
    }

    public void addTwoForTeamA(View view) {
        this.mActivityState.addScoreTeamA(2);
        updateUI();
    }

    public void addOneForTeamA(View view) {
        this.mActivityState.addScoreTeamA(1);
        updateUI();
    }

    public void addThreeForTeamB(View view) {
        this.mActivityState.addScoreTeamB(3);
        updateUI();
    }

    public void addTwoForTeamB(View view) {
        this.mActivityState.addScoreTeamB(2);
        updateUI();
    }

    public void addOneForTeamB(View view) {
        this.mActivityState.addScoreTeamB(1);
        updateUI();
    }

    public void resetScore(View view) {
        this.mActivityState.setScoreTeamA(0).setScoreTeamB(0);
        updateUI();
    }



    private TextView mScoreViewA;
    private TextView mScoreViewB;

    private ActivityState mActivityState;

    private static final String ACTIVITY_STATE = "ACTIVITY_STATE";

}
