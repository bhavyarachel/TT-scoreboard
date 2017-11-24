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

/**
 * Created by Bhavya on 23/11/17.
 * Activity to enter team names playing in the current match
 */
public class PlayingTeamsActivity extends AppCompatActivity {

    @BindView(R.id.spinner_team_name_1)
    Spinner mTeamName1Spinner;
    @BindView(R.id.spinner_team_name_2)
    Spinner mTeamName2Spinner;

    private String mTeam1Name;
    private String mTeam2Name;
    private boolean mIsValidNames = false;
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

    /**
     * On clicking next button, save the team names into shared preference and launch
     * PlayersActivity if all fields are valid.
     */
    @OnClick(R.id.btn_next)
    void onClickNext(){
        mIsValidNames = validateFields();
        if(mIsValidNames){
            CommonMethods.storeTeamNames(this, mTeam1Name, mTeam2Name);
            PlayersActivity.launchActivity(this);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * To validate all the fields
     * @return true if valid ; false if invalid
     */
    private boolean validateFields() {
        boolean isValid = true;
        mTeam1Name = mTeamName1Spinner.getSelectedItem().toString();
        mTeam2Name = mTeamName2Spinner.getSelectedItem().toString();
        if(mTeam1Name.equals(mTeam2Name)){
            isValid = false;
            Toast.makeText(this, getString(R.string.same_teams), Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    /**
     * To initialize team 1 spinner
     */
    private void setTeam1Spinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.team_name_spinner_layout, mTeamNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamName1Spinner.setAdapter(dataAdapter);
    }

    /**
     * To initialize team 2 spinner
     */
    private void setTeam2Spinner(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.team_name_spinner_layout, mTeamNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTeamName2Spinner.setAdapter(dataAdapter);
    }
}
