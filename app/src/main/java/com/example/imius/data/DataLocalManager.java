// Datalocal

package com.example.imius.data;

import android.content.Context;

public class DataLocalManager {
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String USERNAME = "USERNAME";
    private static final String NAME = "NAME";
    private static final String IMAGE = "IMAGE";
    private static final String CHECK_EDIT = "CHECK_EDIT";
    private static final String CHECK_SEARCH = "CHECK_SEARCH";
    private static final String ID_LIBRARY_PLAYLIST = "ID_LIBRARY_PLAYLIST";
    private static final String ID_TRENDING = "ID_TRENDING";
    private static final String ID_TOPIC= "ID_TOPIC";
    private static final String ID_SINGER= "ID_SINGER";
    private static final String ID_CHART= "ID_CHART";





    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setEmail(String value) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(EMAIL, value);
    }

    public static void setPassword(String value) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PASSWORD, value);
    }

    public static void setCheckEdit(boolean value) {
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(CHECK_EDIT, value);
    }

    public static void setCheckSearch(boolean value) {
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(CHECK_SEARCH, value);
    }

    public static void setUsernameData(String value) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(USERNAME, value);
    }

    public static void setNameData(String value) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(NAME, value);
    }

    public static void setIdLibraryPlaylist(int value) {
        DataLocalManager.getInstance().mySharedPreferences.putIntValue(ID_LIBRARY_PLAYLIST, value);
    }
    // Get DATA
    public static void setIdSinger(int value){
        DataLocalManager.getInstance().mySharedPreferences.putIntValue(ID_SINGER, value);
    }
    public static void setIdTrending(int value){
        DataLocalManager.getInstance().mySharedPreferences.putIntValue(ID_TRENDING, value);
    }
    public static void setIdTopic(int value){
        DataLocalManager.getInstance().mySharedPreferences.putIntValue(ID_TOPIC, value);
    }
    public static void setIdChart(int value){
        DataLocalManager.getInstance().mySharedPreferences.putIntValue(ID_CHART, value);
    }


    public static String getEmail() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(EMAIL);
    }

    public static String getPassword() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(PASSWORD);
    }

    public static boolean getCheckEdit() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(CHECK_EDIT);
    }

    public static boolean getCheckSearch() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(CHECK_SEARCH);
    }

    public static String getUsernameData() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(USERNAME);
    }

    public static String getNameData() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(NAME);
    }

    public static String getImage(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(IMAGE);
    }

    public static int getIdLibraryPlaylist(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValue(ID_LIBRARY_PLAYLIST);
    }

    // Get DATA
    public static int getIdSingerData(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValue(ID_SINGER);
    }
    public static int getIdTrending(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValue(ID_TRENDING);
    }
    public static int getIdTopicData(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValue(ID_TOPIC);
    }
    public static int getIdChart(){
        return DataLocalManager.getInstance().mySharedPreferences.getIntValue(ID_CHART);
    }
}

