package com.example.bhavya.tabletennis.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhavya.tabletennis.R;
import com.example.bhavya.tabletennis.utils.AppConstants;
import com.example.bhavya.tabletennis.utils.CommonMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreboardActivity extends AppCompatActivity {

    @BindView(R.id.tv_team1_name)
    TextView mTeam1NameTextView;
    @BindView(R.id.tv_team2_name)
    TextView mTeam2NameTextView;
    @BindView(R.id.tv_team1_players_name)
    TextView mTeam1PlayersNameTextView;
    @BindView(R.id.tv_team2_players_name)
    TextView mTeam2PlayersNameTextView;
    @BindView(R.id.tv_score_team1)
    TextView mScoreTeam1TextView;
    @BindView(R.id.tv_score_team2)
    TextView mScoreTeam2TextView;
    @BindView(R.id.iv_add_point_team1)
    ImageView mAddOnePointTeam1;
    @BindView(R.id.iv_minus_point_team1)
    ImageView mMinusOnePointTeam1;
    @BindView(R.id.iv_add_point_team2)
    ImageView mAddOnePointTeam2;
    @BindView(R.id.iv_minus_point_team2)
    ImageView mMinusOnePointTeam2;

    private int mScoreTeam1;
    private int mScoreTeam2;

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, ScoreboardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        ButterKnife.bind(this);
        displayTeamNames();
        displayPlayersInEachTeam();
        setInitialScores();
    }

    @Override
    public void onBackPressed() {
        confirmGameOver();
    }

    @OnClick(R.id.iv_add_point_team1)
    void onAddOnePointForTeam1() {
        mScoreTeam1++;
        mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
        saveTeam1ScoreIntoSharedPref();
    }

    @OnClick(R.id.iv_add_point_team2)
    void onAddOnePointForTeam2() {
        mScoreTeam2++;
        mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
        saveTeam2ScoreIntoSharedPref();
    }

    @OnClick(R.id.iv_minus_point_team1)
    void onMinusOnePointForTeam1() {
        if (mScoreTeam1 > 0) {
            mScoreTeam1--;
            mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
            saveTeam1ScoreIntoSharedPref();
        }
    }

    @OnClick(R.id.iv_minus_point_team2)
    void onMinusOnePointForTeam2() {
        if(mScoreTeam2 > 0){
            mScoreTeam2--;
            mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
            saveTeam2ScoreIntoSharedPref();
        }
    }

    @OnClick(R.id.btn_game_over)
    void onClickGameOver(){
       confirmGameOver();
    }

    private void confirmGameOver(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm game over")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CommonMethods.deleteSharedPreference(ScoreboardActivity.this,
                                AppConstants.PLAYING_TEAMS_DETAILS);
                        PlayingTeamsActivity.launchActivity(ScoreboardActivity.this);
                        finish();
                    }
                }).setNegativeButton(
                "No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void displayTeamNames() {
        mTeam1NameTextView.setText(CommonMethods.getTeam1Name(this));
        mTeam2NameTextView.setText(CommonMethods.getTeam2Name(this));
    }

    private void displayPlayersInEachTeam() {
        mTeam1PlayersNameTextView.setText(CommonMethods.getTeam1Players(this));
        mTeam2PlayersNameTextView.setText(CommonMethods.getTeam2Players(this));
    }

    private void setInitialScores() {
        mScoreTeam1 = 0;
        mScoreTeam2 = 0;
        CommonMethods.storeTeamOneScore(this, mScoreTeam1);
        CommonMethods.storeTeamTwoScore(this, mScoreTeam2);
        mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
        mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
    }

    private void saveTeam1ScoreIntoSharedPref(){
        CommonMethods.storeTeamOneScore(this, mScoreTeam1);
    }

    private void saveTeam2ScoreIntoSharedPref(){
        CommonMethods.storeTeamTwoScore(this, mScoreTeam2);
    }
}
