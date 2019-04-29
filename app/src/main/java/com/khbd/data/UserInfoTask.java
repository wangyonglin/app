package com.khbd.data;

import android.content.Context;

import com.data.UserInfo;
import com.khbd.app.R;

import java.io.IOException;

import javakit.jackson.JacksonUtil;
import javakit.network.WebClient;

public class UserInfoTask {
    public static void addUserInfo(Context context,String username, String password, String phone) throws Exception{
        UserInfo userInfo = new UserInfo(username,password,phone);
        String json =null;
        try {
            json = JacksonUtil.obj2json(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebClient.postJson(context.getString(R.string.url_user_add), json, new WebClient.ResultCallback<byte[]>() {
            @Override
            public void success(byte[] res) throws IOException {
                System.out.print(res.toString());
            }

            @Override
            public void failure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void authUserInfo(Context context,String usename,String password){
        String url= context.getString(R.string.url_user_auth)+"/Username="+usename+"&Password="+password;
        WebClient.get(url, new WebClient.ResultCallback<byte[]>() {
            @Override
            public void success(byte[] res) throws IOException {
                System.out.print(res.toString());
            }
            @Override
            public void failure(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
