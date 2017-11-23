package com.example.bhavya.tabletennis.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mobomo LLC on 23/11/17.
 */

public class CommonMethods {

    public static void storeTeamNames(Activity activity, String team1Name, String team2Name) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putString(AppConstants.TEAM_1_NAME, team1Name);
        editor.putString(AppConstants.TEAM_2_NAME, team2Name);
        editor.apply();
    }

    public static void storePlayerNames(Activity activity, String team1Players, String team2Players) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putString(AppConstants.TEAM_1_PLAYERS, team1Players);
        editor.putString(AppConstants.TEAM_2_PLAYERS, team2Players);
        editor.apply();
    }

    public static String getTeam1Name(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_1_NAME, AppConstants.DEFAULT_TEAM_NAME);
    }

    public static String getTeam2Name(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_2_NAME, AppConstants.DEFAULT_TEAM_NAME);
    }

    public static String getTeam1Players(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences .getString(AppConstants.TEAM_1_PLAYERS, AppConstants.DEFAULT_PLAYER_NAME);
    }

    public static String getTeam2Players(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_2_PLAYERS, AppConstants.DEFAULT_PLAYER_NAME);
    }

    public static void storeTeamOneScore(Activity activity, int score) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putInt(AppConstants.TEAM1_CURRENT_SCORE, score);
        editor.apply();
    }

    public static void storeTeamTwoScore(Activity activity, int score) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putInt(AppConstants.TEAM2_CURRENT_SCORE, score);
        editor.apply();
    }

    public static void deleteSharedPreference(Context context, String preferenceName) {
        SharedPreferences preference =
                context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        preference.edit().clear().apply();
    }
}
