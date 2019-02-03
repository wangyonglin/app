package com.wangyonglin.app.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.heylyn.jackson.JacksonUtil;
import com.heylyn.network.HttpClient;
import com.heylyn.network.ResultCallback;

import java.io.IOException;


public class JSONAPI {
    public static <T> void CALL(String uri,final Class<T> clazz,final ShellResultCallback<T> res)  throws IOException
    {
        HttpClient.urlResultString(uri, new ResultCallback<String>() {

            public void failure(RuntimeException e) {
                // TODO Auto-generated method stub

            }

            public void success(String data) {
                // TODO Auto-generated method stub
                try {
                    JsonNode json = JacksonUtil.readTree(data.toString());
                    JsonNode success = json.get("success");
                    if(success.asBoolean()==false) {
                        System.out.println("false");
                    }
                    JsonNode result = json.get("result");
                    Shell shell = new Shell();
                    shell.setNumberOfElements(result.get("numberOfElements").asInt());
                    shell.setSize(result.get("size").asInt());
                    shell.setTotalElements(result.get("totalElements").asInt());
                    shell.setTotalPages(result.get("totalPages").asInt());
                    JsonNode content =result.get("content");

                    res.data(shell,JacksonUtil.json2list(content, clazz));
                } catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }});

    }
    public static <T> void UUID(String uuid,final Class<T> tClass,final ShellResultCallback<T> res)throws IOException{

    }
}
