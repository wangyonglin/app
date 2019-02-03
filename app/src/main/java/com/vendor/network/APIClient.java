package com.vendor.network;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.khbd.data.Video;
import com.util.APIURL;


import java.io.IOException;
import javakit.jackson.JacksonUtil;
import javakit.network.HttpClientResponse;
import javakit.result.ResultCallback;
import okhttp3.ResponseBody;
public class APIClient {
    public static void id(String uuid, ResultCallback<Video> callback){
        HttpClientResponse.String(APIURL.UUID(uuid), new HttpClientResponse.ResultCallback<String>() {
            @Override
            public void success(String res) {
                try {
                    JsonNode result = JacksonUtil.readTree(res).get("result");
                    Video video= JacksonUtil.json2pojo(result.toString(),Video.class);
                    callback.resove(video);

                } catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    callback.reject(new RuntimeException(e.getMessage()));
                } catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    callback.reject(new RuntimeException(e.getMessage()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    callback.reject(new RuntimeException(e.getMessage()));
                } catch (Exception e) {
                    callback.reject(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void failure(Exception e) {
                callback.reject(e);
            }
        });
    }
}
