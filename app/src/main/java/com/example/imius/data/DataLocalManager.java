package com.example.imius.data;

import android.content.Context;

public class DataLocalManager {
    private static final String KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL";
    private static final String CHECK_FROM_LIBRARY = "CHECK_FROM_LIBRARY";
    private static final String CHECK_LOGIN = "CHECK_LOGIN";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String USERNAME = "USERNAME";
    private static final String NAME = "NAME";
    private static final String IMAGE = "IMAGE";
    private static final String CHECK_EDIT = "CHECK_EDIT";
    private static final String CHECK_SEARCH = "CHECK_SEARCH";
    private static final String ID_LIBRARY_PLAYLIST = "ID_LIBRARY_PLAYLIST";



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

    public static void setFirstInstall(boolean value) {
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(KEY_FIRST_INSTALL, value);
    }
    public static void setCheckFromLibrary(boolean value) {
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(CHECK_FROM_LIBRARY, value);
    }

    public static void setCheckLogin(boolean value) {
        DataLocalManager.getInstance().mySharedPreferences.putBoolean(CHECK_LOGIN, value);
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

    public static String getEmail() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(EMAIL);
    }

    public static String getPassword() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(PASSWORD);
    }

    public static boolean getFirstInstall() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL);
    }

    public static boolean getCheckLogin() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(CHECK_LOGIN);
    }

    public static boolean getCheckFromLibrary() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(CHECK_FROM_LIBRARY);
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

    public static void clearDataLocal (){
        DataLocalManager.getInstance().mySharedPreferences.clearShare();
    }

}
