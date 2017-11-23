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

public class PlayersActivity extends AppCompatActivity {

    @BindView(R.id.et_team1_players)
    EditText team1PlayersEditText;
    @BindView(R.id.et_team2_players)
    EditText team2PlayersEditText;
    @BindView(R.id.tv_player_in_team2)
    TextView mTextViewPlayerInTeam2;
    @BindView(R.id.tv_player_in_team1)
    TextView mTextViewPlayerInTeam1;

    private String team1Players;
    private String team2Players;
    private boolean isValidNames = false;

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

    @OnClick(R.id.btn_start_game)
    void onStartGameClick(){
        isValidNames = validateFields();
        if(isValidNames){
            CommonMethods.storePlayerNames(this, team1Players, team2Players);
            ScoreboardActivity.launchActivity(this);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    private void getTeamNames() {
        String mTeam1Name = CommonMethods.getTeam1Name(this);
        mTextViewPlayerInTeam1.setText(getString(R.string.team1_players).concat(mTeam1Name));
        String mTeam2Name = CommonMethods.getTeam2Name(this);
        mTextViewPlayerInTeam2.setText(getString(R.string.team2_players).concat(mTeam2Name));
    }

    private boolean validateFields() {
        boolean isValid = true;
        team1Players = team1PlayersEditText.getText().toString();
        team2Players = team2PlayersEditText.getText().toString();
        if(TextUtils.isEmpty(team1Players)){
            isValid= false;
            Toast.makeText(this, getString(R.string.invalidTeam1PlayerNames), Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(team2Players)){
            isValid = false;
            Toast.makeText(this, getString(R.string.invalidTeam2PlayerNames), Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }
}
