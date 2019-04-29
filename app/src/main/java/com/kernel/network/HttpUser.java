package com.kernel.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.result.ResultCallback;
import javakit.util.JavaKitJsonUtils;

public class HttpUser implements HttpUserInterface{

    public  void Jumbotron(ResultCallback<String> result){
        JavaKitClientResponse.get("http://apis.eeob.com/app/all", new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                try {
                    String str=  JavaKitJsonUtils.readTree(res).get("jumbotron").textValue();
                    result.resove(str);
                } catch (IOException e) {
                    result.reject(e);
                }catch (NullPointerException e){
                    result.reject(new RuntimeException("http jumbotron fail"));
                }
            }

            @Override
            public void failure(Exception e) {

                result.reject(e);
            }
        });

    }


    public  void Time(String user, ResultCallback<Integer> resultCallback) {
        String uri="http://apis.eeob.com/user/auth";
        ObjectNode root = JavaKitJsonUtils.getInstance().createObjectNode();
        String json="";
        root.put("user",user);
        try {
            json=     JavaKitJsonUtils.getInstance().writeValueAsString(root);
        } catch (JsonProcessingException e) {
            resultCallback.reject(new RuntimeException("make json request fail"));
        }

        JavaKitClientResponse.post(uri,json,new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {



                try {
                    JsonNode jsonNodeMain= JavaKitJsonUtils.readTree(res).get("data");
                    resultCallback.resove(Integer.valueOf(jsonNodeMain.textValue()));

                } catch (IOException e) {
                    resultCallback.reject(new RuntimeException("network request parse json fail"));
                }catch (NullPointerException e){
                    resultCallback.reject(new RuntimeException("data not null"));
                }

            }

            @Override
            public void failure(Exception e) {
                resultCallback.reject(new RuntimeException("network request failure json"));
            }
        });
    }
}
