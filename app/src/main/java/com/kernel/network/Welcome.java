package com.kernel.network;

import android.content.Context;



import com.kernel.UserDataUtil;

import com.khbd.app.LoginActivity;
import com.khbd.app.R;

import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;

public class Welcome{

    public Welcome(Context context, String username, String password, LoginCallback loginCalllback){
        StringBuffer buffer= new StringBuffer();
        buffer.append(context.getString(R.string.url_user_login));
        buffer.append("?user=").append(username).append("&pass=").append(password);

        JavaKitClientResponse.get(buffer.toString(), new JavaKitClientResponseCallback<String>() {
            @Override
            public void success( String res) {

                if(res.isEmpty()&&res.equals("")){
                    loginCalllback.login_failure(new RuntimeException("login fail"));
                }else {
                    try {
                        String  user  = JavaKitJsonUtils.readTree(res).get("user").textValue();
                        String  token  = JavaKitJsonUtils.readTree(res).get("token").textValue();
                        UserDataUtil.setUser(context,user);
                        UserDataUtil.setToken(context,token);
                        loginCalllback.login_success(context);
                    } catch (IOException e) {
                        loginCalllback.login_failure(new RuntimeException("info login fail"));
                    }
                }

            }

            @Override
            public void failure(Exception e) {
                loginCalllback.login_failure(e);
            }
        });
    }


}
