package com.example.bhavya.tabletennis.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bhavya on 23/11/17.
 * Common Methods
 */

public class CommonMethods {

    /**
     * To store team names into shared preference
     * @param activity activity context
     * @param team1Name team 1 name
     * @param team2Name team 2 name
     */
    public static void storeTeamNames(Activity activity, String team1Name, String team2Name) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putString(AppConstants.TEAM_1_NAME, team1Name);
        editor.putString(AppConstants.TEAM_2_NAME, team2Name);
        editor.apply();
    }

    /**
     * To store the player names into shared preference
     * @param activity activity context
     * @param team1Players team 1 players
     * @param team2Players team 2 players
     */
    public static void storePlayerNames(Activity activity, String team1Players, String team2Players) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putString(AppConstants.TEAM_1_PLAYERS, team1Players);
        editor.putString(AppConstants.TEAM_2_PLAYERS, team2Players);
        editor.apply();
    }

    /**
     * To get the team 1 name from shared preference
     * @param activity activity context
     * @return team 1 name
     */
    public static String getTeam1Name(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_1_NAME, AppConstants.DEFAULT_TEAM_NAME);
    }

    /**
     * To get the team2 name from shared preference
     * @param activity activity context
     * @return team 2 name
     */
    public static String getTeam2Name(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_2_NAME, AppConstants.DEFAULT_TEAM_NAME);
    }

    /**
     * To get the team 1 players
     * @param activity activity context
     * @return team 1 player names
     */
    public static String getTeam1Players(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences .getString(AppConstants.TEAM_1_PLAYERS, AppConstants.DEFAULT_PLAYER_NAME);
    }

    /**
     * To get the team 2 player names
     * @param activity activity context
     * @return team 2 player names
     */
    public static String getTeam2Players(Activity activity) {
        SharedPreferences sharedPreferences =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AppConstants.TEAM_2_PLAYERS, AppConstants.DEFAULT_PLAYER_NAME);
    }

    /**
     * To save the updates score of team1 into shared preference
     * @param activity activity context
     * @param score updated score
     */
    public static void storeTeamOneScore(Activity activity, int score) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putInt(AppConstants.TEAM1_CURRENT_SCORE, score);
        editor.apply();
    }

    /**
     * To save the updates score of team2 into shared preference
     * @param activity activity context
     * @param score updated score
     */
    public static void storeTeamTwoScore(Activity activity, int score) {
        SharedPreferences.Editor editor =
                activity.getSharedPreferences(AppConstants.PLAYING_TEAMS_DETAILS, Context.MODE_PRIVATE).edit();
        editor.putInt(AppConstants.TEAM2_CURRENT_SCORE, score);
        editor.apply();
    }

    /**
     * To reset the entire match and start new game by deleting the info stored in shared preference
     * @param context context
     * @param preferenceName preference name
     */
    public static void deleteSharedPreference(Context context, String preferenceName) {
        SharedPreferences preference =
                context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        preference.edit().clear().apply();
    }
}
