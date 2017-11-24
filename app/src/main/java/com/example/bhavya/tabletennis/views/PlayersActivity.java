package com.example.bhavya.tabletennis.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhavya.tabletennis.R;
import com.example.bhavya.tabletennis.utils.CommonMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bhavya on 23/11/17.
 * Activity to enter players playing in the current match
 */
public class PlayersActivity extends AppCompatActivity {

    @BindView(R.id.et_team1_players)
    EditText mTeam1PlayersEditText;
    @BindView(R.id.et_team2_players)
    EditText mTeam2PlayersEditText;
    @BindView(R.id.tv_player_in_team2)
    TextView mTextViewPlayerInTeam2;
    @BindView(R.id.tv_player_in_team1)
    TextView mTextViewPlayerInTeam1;

    private String mTeam1Players;
    private String mTeam2Players;
    private boolean mIsValidNames = false;

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, PlayersActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);
        getTeamNames();
    }

    /**
     * On clicking start game button, save the player names to the shared preference and launch
     * Scoreboard Activity if all fields are valid
     */
    @OnClick(R.id.btn_start_game)
    void onStartGameClick(){
        mIsValidNames = validateFields();
        if(mIsValidNames){
            CommonMethods.storePlayerNames(this, mTeam1Players, mTeam2Players);
            ScoreboardActivity.launchActivity(this);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * To get the team names from shared preference
     */
    private void getTeamNames() {
        String mTeam1Name = CommonMethods.getTeam1Name(this);
        mTextViewPlayerInTeam1.setText(getString(R.string.team1_players).concat(mTeam1Name));
        String mTeam2Name = CommonMethods.getTeam2Name(this);
        mTextViewPlayerInTeam2.setText(getString(R.string.team2_players).concat(mTeam2Name));
    }

    /**
     * To validate all the fields
     * @return true if valid ; false if invalid
     */
    private boolean validateFields() {
        boolean isValid = true;
        mTeam1Players = mTeam1PlayersEditText.getText().toString();
        mTeam2Players = mTeam2PlayersEditText.getText().toString();
        if(TextUtils.isEmpty(mTeam1Players.trim())){
            isValid= false;
            Toast.makeText(this, getString(R.string.invalidTeam1PlayerNames), Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(mTeam2Players.trim())){
            isValid = false;
            Toast.makeText(this, getString(R.string.invalidTeam2PlayerNames), Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }
}
