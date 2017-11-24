package com.example.bhavya.tabletennis.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bhavya.tabletennis.R;
import com.example.bhavya.tabletennis.utils.AppConstants;
import com.example.bhavya.tabletennis.utils.CommonMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bhavya on 23/11/17.
 * Activity to enter the score and display the playing teams, players and the updated score
 */
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
    @BindView(R.id.iv_team_1_serve)
    ImageView mTeam1ServeImageView;
    @BindView(R.id.iv_team_2_serve)
    ImageView mTeam2ServeImageView;
    @BindView(R.id.tv_game_status_team_1)
    TextView mGameStatusTeam1TextView;
    @BindView(R.id.tv_game_status_team_2)
    TextView mGameStatusTeam2TextView;
    @BindView(R.id.layout_game_status)
    LinearLayout mGameStatusLayout;
    @BindView(R.id.tv_duece)
    TextView mDeuceTextView;

    private int mScoreTeam1;
    private int mScoreTeam2;
    private int mScoreSum = 0;
    private boolean mIsTeam1Serve = true;

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

    /**
     * On clicking the plus button for team 1, add 1 point to Team1
     */
    @OnClick(R.id.iv_add_point_team1)
    void onAddOnePointForTeam1() {
        mScoreTeam1++;
        mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
        saveTeam1ScoreIntoSharedPref();
        switchServeBasedOnScoreSum();
    }

    /**
     * On clicking the plus button for team 2, add 1 point to Team2
     */
    @OnClick(R.id.iv_add_point_team2)
    void onAddOnePointForTeam2() {
        mScoreTeam2++;
        mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
        saveTeam2ScoreIntoSharedPref();
        switchServeBasedOnScoreSum();
    }

    /**
     * On clicking the minus button for team 1, subtract 1 point from Team1
     */
    @OnClick(R.id.iv_minus_point_team1)
    void onMinusOnePointForTeam1() {
        if (mScoreTeam1 > 0) {
            mScoreTeam1--;
            mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
            saveTeam1ScoreIntoSharedPref();
            switchServeBasedOnScoreSum();
        }
    }

    /**
     * On clicking the minus button for team 2, subtract 1 point from Team2
     */
    @OnClick(R.id.iv_minus_point_team2)
    void onMinusOnePointForTeam2() {
        if(mScoreTeam2 > 0){
            mScoreTeam2--;
            mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
            saveTeam2ScoreIntoSharedPref();
            switchServeBasedOnScoreSum();
        }
    }

    /**
     * On clicking game over button, display alert to user for confirmation
     */
    @OnClick(R.id.btn_game_over)
    void onClickGameOver(){
       confirmGameOver();
    }

    /**
     * For serve --> On clicking TT bat1, set alpha to TTbat2
     */
    @OnClick(R.id.iv_team_1_serve)
    void onClickTTTeam1Bat(){
        mTeam2ServeImageView.setAlpha(0.5f);
        mTeam1ServeImageView.setAlpha(1f);
        mIsTeam1Serve = true;
    }

    /**
     * For serve --> On clicking TT bat2, set alpha to TTbat1
     */
    @OnClick(R.id.iv_team_2_serve)
    void onClickTTTeam2Bat(){
        mTeam1ServeImageView.setAlpha(0.5f);
        mTeam2ServeImageView.setAlpha(1f);
        mIsTeam1Serve = false;
    }

    /**
     * To confirm game over
     */
    private void confirmGameOver(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirm_game_over))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CommonMethods.deleteSharedPreference(ScoreboardActivity.this,
                                AppConstants.PLAYING_TEAMS_DETAILS);
                        PlayingTeamsActivity.launchActivity(ScoreboardActivity.this);
                        finish();
                    }
                }).setNegativeButton(
                getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * To get the names of the currently playing teams and display it
     */
    private void displayTeamNames() {
        mTeam1NameTextView.setText(CommonMethods.getTeam1Name(this));
        mTeam2NameTextView.setText(CommonMethods.getTeam2Name(this));
    }

    /**
     * To get the names of the players and display it
     */
    private void displayPlayersInEachTeam() {
        mTeam1PlayersNameTextView.setText(CommonMethods.getTeam1Players(this));
        mTeam2PlayersNameTextView.setText(CommonMethods.getTeam2Players(this));
    }

    /**
     * To set initial scores of both teams to 0 and save into shared preference
     */
    private void setInitialScores() {
        mScoreTeam1 = 0;
        mScoreTeam2 = 0;
        CommonMethods.storeTeamOneScore(this, mScoreTeam1);
        CommonMethods.storeTeamTwoScore(this, mScoreTeam2);
        mScoreTeam1TextView.setText(String.valueOf(mScoreTeam1));
        mScoreTeam2TextView.setText(String.valueOf(mScoreTeam2));
        setAlphaForTeam2TTBat();
    }

    /**
     * Set alpha for team 2 TT bat initially
     */
    private void setAlphaForTeam2TTBat() {
        mIsTeam1Serve = true;
        mTeam2ServeImageView.setAlpha(0.5f);
    }

    /**
     * To save updated team1 score into shared preference
     */
    private void saveTeam1ScoreIntoSharedPref(){
        CommonMethods.storeTeamOneScore(this, mScoreTeam1);
    }

    /**
     * To save updated team2 score into shared preference
     */
    private void saveTeam2ScoreIntoSharedPref(){
        CommonMethods.storeTeamTwoScore(this, mScoreTeam2);
    }

    /**
     * To switch serve based on score sum
     */
    private void switchServeBasedOnScoreSum(){
        mScoreSum = mScoreTeam1 + mScoreTeam2;
        if(mScoreSum <= 40){
            if(mScoreSum % 5 == 0){
                if(mIsTeam1Serve){
                    mIsTeam1Serve = false;
                    mTeam1ServeImageView.setAlpha(0.5f);
                    mTeam2ServeImageView.setAlpha(1f);
                } else {
                    mIsTeam1Serve = true;
                    mTeam2ServeImageView.setAlpha(0.5f);
                    mTeam1ServeImageView.setAlpha(1f);
                }
            }
            if((mScoreSum == 40) && (mScoreTeam1 == mScoreTeam2)){
                mDeuceTextView.setVisibility(View.VISIBLE);
                mGameStatusLayout.setVisibility(View.GONE);
            } else {
                mDeuceTextView.setVisibility(View.GONE);
                mGameStatusLayout.setVisibility(View.GONE);
            }
        }

        if(mScoreSum > 40) {
            if((mScoreTeam2 - mScoreTeam1) == 1){
                mDeuceTextView.setVisibility(View.GONE);
                mGameStatusLayout.setVisibility(View.VISIBLE);
                mGameStatusTeam2TextView.setText(getString(R.string.advantage));
                mGameStatusTeam2TextView.setVisibility(View.VISIBLE);
                mGameStatusTeam1TextView.setVisibility(View.INVISIBLE);
            } else if((mScoreTeam1 - mScoreTeam2 == 1)){
                mDeuceTextView.setVisibility(View.GONE);
                mGameStatusLayout.setVisibility(View.VISIBLE);
                mGameStatusTeam1TextView.setText(getString(R.string.advantage));
                mGameStatusTeam1TextView.setVisibility(View.VISIBLE);
                mGameStatusTeam2TextView.setVisibility(View.INVISIBLE);
            } else if((mScoreTeam2 - mScoreTeam1 == 2)){
                mDeuceTextView.setVisibility(View.GONE);
                mGameStatusLayout.setVisibility(View.VISIBLE);
                mGameStatusTeam2TextView.setText(getString(R.string.winner));
                mGameStatusTeam2TextView.setVisibility(View.VISIBLE);
                mGameStatusTeam1TextView.setVisibility(View.INVISIBLE);
            } else if((mScoreTeam1 - mScoreTeam2 == 2)){
                mDeuceTextView.setVisibility(View.GONE);
                mGameStatusLayout.setVisibility(View.VISIBLE);
                mGameStatusTeam1TextView.setText(getString(R.string.winner));
                mGameStatusTeam1TextView.setVisibility(View.VISIBLE);
                mGameStatusTeam2TextView.setVisibility(View.INVISIBLE);
            }
        }
    }
}
