package com.kernel.network;

import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.result.ResultCallback;
import javakit.util.JavaKitJsonUtils;

public class HttpMain implements HttpMainInterface {
        public void Version(ResultCallback<String> result){
            JavaKitClientResponse.get("http://apis.eeob.com/app/version", new JavaKitClientResponseCallback<String>() {
                @Override
                public void success(String res) {
                    try {
                        String str=  JavaKitJsonUtils.readTree(res).get("version").textValue();
                        result.resove(str);
                    } catch (IOException e) {
                        result.reject(e);
                    }catch (NullPointerException e){
                        result.reject(new RuntimeException("result: version fail"));
                    }
                }

                @Override
                public void failure(Exception e) {
                    result.reject(e);
                }
            });

        }
    public void Download(ResultCallback<String> result){
        JavaKitClientResponse.get("http://apis.eeob.com/app/version", new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                try {
                    String str=  JavaKitJsonUtils.readTree(res).get("download").textValue();
                    result.resove(str);
                } catch (IOException e) {
                    result.reject(e);
                }catch (NullPointerException e){
                    result.reject(new RuntimeException("result: download fail"));
                }
            }

            @Override
            public void failure(Exception e) {
                result.reject(e);
            }
        });

    }
}
