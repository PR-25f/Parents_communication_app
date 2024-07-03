package com.example.knowme;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager instance;
    private static Context ctx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_MOBILE="mobile";
    private static final String KEY_RMOBILE="rmobile";
    private static final String KEY_CLS="cls";
    private static final String KEY_TYPE="type";


    public SharedPrefManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }


    public boolean userLogin(String username,String mobile,String rmobile,String cls,String type){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_MOBILE,mobile);
        editor.putString(KEY_RMOBILE,rmobile);
        editor.putString(KEY_CLS,cls);
        editor.putString(KEY_TYPE,type);
        editor.apply();
        return true;

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null)!=null){
            return true;
        }else{
            return false;
        }
    }

    public String getCls(){
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CLS,null);
    }

    public String getRmobile(){
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_RMOBILE,null);
    }

    public boolean logout(){
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
