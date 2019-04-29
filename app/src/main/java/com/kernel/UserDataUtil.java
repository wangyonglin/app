package com.kernel;

import android.content.Context;
import android.content.SharedPreferences;

public class UserDataUtil {
    private static String UserInfo="UserInfo";
    private static String Username="Username";
    private static String Password="Password";
    private static String Token="Token";
    private static String user;
    private static String pass;
    private static String token;
    public static void setUser(Context context,String user){
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Username,user);
        editor.commit();
        return;
    }
    public static String getUser(Context context){
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        return settings.getString(Username,"");
    }

    public static String getPass(Context context) {
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        return settings.getString(Password,"");
    }

    public static void setPass(Context context,String pass) {
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Password,pass);
        editor.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        return settings.getString(Token,"");
    }

    public static void setToken(Context context,String token) {
        SharedPreferences settings = context.getSharedPreferences(UserInfo, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Token,token);
        editor.commit();
    }


}
