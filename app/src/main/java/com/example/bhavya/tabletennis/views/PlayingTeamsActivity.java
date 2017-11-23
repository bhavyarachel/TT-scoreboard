package com.example.bhavya.tabletennis.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bhavya.tabletennis.R;
import com.example.bhavya.tabletennis.utils.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayingTeamsActivity extends AppCompatActivity {

    @BindView(R.id.spinner_team_name_1)
    Spinner mTeamName1Spinner;
    @BindView(R.id.spinner_team_name_2)
    Spinner mTeamName2Spinner;

    private String team1Name;
    private String team2Name;
    private boolean isValidNames = false;
    private List<String> mTeamNames = new ArrayList<>();

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, PlayingTeamsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_teams);
        ButterKnife.bind(this);
        mTeamNames = Arrays.asList(getResources().getStringArray(R.array.team_names));
        setTeam1Spinner();
        setTeam2Spinner();
    }

    @OnClick(R.id.btn_next)
    void onClickNext(){
        isValidNames = validateFields();
        if(isValidNames){
            CommonMethods.storeTeamNames(this, team1Name, team2Name);
            PlayersActivity.launchActivity(this);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateFields() {
        boolean isValid = true;
        team1Name = mTeamName1Spinner.getSelectedItem().toString();
        team2Name = mTeamName2Spinner.getSelectedItem().toString();
        if(team1Name.equals(team2Name)){
            isValid = false;
            Toast.makeText(this, getString(R.string.same_teams), Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    private void setTeam1Spinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.team_name_spinner_layout, mTeamNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamName1Spinner.setAdapter(dataAdapter);
    }

    private void setTeam2Spinner(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.team_name_spinner_layout, mTeamNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamName2Spinner.setAdapter(dataAdapter);
    }
}
