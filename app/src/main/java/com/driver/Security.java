package com.driver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.data.TokenInfo;
import com.kernel.LoginCallback;
import com.kernel.UserDataUtil;
import com.khbd.app.MainActivity;
import com.khbd.app.view.LoginDialog;
import com.khbd.app.view.LoginDialogFactoryCallback;
import com.khbd.data.ServerResponse;

import java.io.FileNotFoundException;

import javakit.util.JavaKitJsonUtils;

public class Security {
    private static Boolean auth_status;
    public String user="";
    public String token="";
    public static Boolean auth(@NonNull Context context){
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUser(UserDataUtil.getUser(context));
        tokenInfo.setToken(UserDataUtil.getToken(context));
        try {
            String json= JavaKitJsonUtils.obj2json(tokenInfo);
            ServerResponse.auth(context, json, tokenInfo.getToken(), new ServerResponse.ServerResponseCallback() {
                @Override
                public void login_success() {
                    auth_status=true;
                }

                @Override
                public void login_failure(Exception e) {
                    e.printStackTrace();
                    auth_status= false;
                }
            });
        } catch (Exception e) {
            auth_status= false;
        }
        return auth_status;
    }

    public static void login(@NonNull Context context, LoginCallback<String> loginCallback){
        LoginDialog.build(context, new LoginDialogFactoryCallback() {
            @Override
            public void login_success(@NonNull Context context) {
                loginCallback.login_success(context,"login ok");
            }

            @Override
            public void login_failure(Exception e) {
                loginCallback.login_failure(e);
            }
        });

    }
}
