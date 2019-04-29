package com.khbd.data;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.khbd.app.R;

import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.security.RSAUtils;
import javakit.util.JavaKitJsonUtils;

public class ServerResponse {
    private static String TAG="ServerResponse";
    public static void auth(Context context,String json,String token,ServerResponseCallback responseCallback) throws RuntimeException{
        try {
            JavaKitClientResponse.post(context.getString(R.string.url_user_auth), json, new JavaKitClientResponseCallback<String>() {
                @Override
                public void success(String res) {
                    try {
                        JsonNode jsonNode= JavaKitJsonUtils.readTree(res);
                        if(jsonNode.isNull()){
                            throw new RuntimeException("返回检证失败");
                        }else{
                           String data= jsonNode.get("data").textValue();
                           String sign =jsonNode.get("sign").textValue();
                            if(!token.isEmpty()&&token!=null){
                                if(RSAUtils.verify(data,RSAUtils.getPublicKey(token),sign)){
                                    responseCallback.login_success();

                                }else{
                                    responseCallback.login_failure(new RuntimeException("Token 检证失误"));
                                }
                            }else{
                                responseCallback.login_failure(new RuntimeException("Token 返回为空"));
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
                @Override
                public void failure(Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
   public interface ServerResponseCallback{
       default void login_success(){}
       default void login_failure(Exception e){

       };
   }
}
