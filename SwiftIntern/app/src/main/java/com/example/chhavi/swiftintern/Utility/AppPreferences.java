package com.example.chhavi.swiftintern.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chhavi on 11/7/15.
 */
public class AppPreferences {
    public static void setBasicProfile(Context mContext, String name, String email,String id) {
        if(name == null || email == null) {
            return;
        }
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("user_id", id);
        editor.commit();
    }

    public static void setLoggedInAsTrue(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("loggedin", true);
        editor.commit();
    }

    public static int getUserId(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        return prefs.getInt("userId", 311);
    }

    public static boolean isLoggedIn(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        return prefs.getBoolean("loggedin", false);
    }

    public static String getUsername(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        return prefs.getString("name", null);
    }

    public static String getUserPhoto(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("basicProfile", 0);
        return prefs.getString("email",null);
    }



}
