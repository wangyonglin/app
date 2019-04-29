package com.khbd.data;

import android.content.Context;

import com.data.UserInfo;
import com.khbd.app.R;

import java.io.IOException;

import javakit.jackson.JacksonUtil;
import javakit.network.WebClient;

public class UserInfoTask {
    public static void addUserInfo(Context context,String username, String password, String phone,WebClient.ResultCallback resultCallback) throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        String json =null;
        try {
            json = JacksonUtil.obj2json(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebClient.postJson(context.getString(R.string.url_user_add), json,resultCallback);
    }

    public static void authUserInfo(Context context,String usename,String password,WebClient.ResultCallback resultCallback){
        String url= context.getString(R.string.url_user_auth)+"?user="+usename+"&pass="+password;
        WebClient.get(url,resultCallback);
    }
}
