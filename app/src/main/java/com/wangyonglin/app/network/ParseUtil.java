package com.wangyonglin.app.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.heylyn.network.HttpClient;
import com.heylyn.network.ResultCallback;

import com.wangyonglin.app.domain.ItemClass;
import com.wangyonglin.app.domain.TelevisionBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vendor.json.JacksonUtil;

public class ParseUtil {
    public static void toTelevisions(String str,ResultCallback<List> callback){
        final List<TelevisionBuilder> lists= new ArrayList<TelevisionBuilder>();
        try {
            JsonNode jsonNode = JacksonUtil.readTree(str.toString());
            JsonNode TotalElements = jsonNode.get("result").get("TotalElements");
            JsonNode ContentNode = jsonNode.get("result").get("Content");
            System.out.println(ContentNode.toString());
            if(ContentNode == null){
                throw new NullPointerException("HTTP 请求的内容为空 result->Content");
            }
            Iterator<JsonNode> realElements = ContentNode.elements();

            while (realElements.hasNext()) {
                JsonNode realElement = realElements.next();
                String cover = realElement.get("cover").toString().replace("\"", "");
                String title = realElement.get("title").toString().replace("\"", "");
                String uuid = realElement.get("infoid").toString().replace("\"", "");
                JsonNode ListNode =  realElement.get("list");
                Iterator<JsonNode> listsElements = ListNode.elements();
                List<ItemClass> itemClasses = new ArrayList<>();
                while (listsElements.hasNext()){
                    JsonNode listElement = listsElements.next();

                    ItemClass itemClass= new ItemClass();
                    itemClass.listid=listElement.get("listid").toString().replace("\"", "");
                    itemClass.overview=listElement.get("overview").toString().replace("\"", "");
                    itemClass.download=listElement.get("download").toString().replace("\"", "");
                    itemClass.name=listElement.get("name").toString().replace("\"", "");
                    itemClass.picture=listElement.get("picture").toString().replace("\"", "");
                    itemClass.released=listElement.get("released").toString().replace("\"", "");
                    itemClasses.add(itemClass);
                }
                lists.add( TelevisionBuilder.create(title,cover,uuid,itemClasses));


            }
            callback.success(lists);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());

        }
    }

    public static void call(String url,ResultCallback callback){
        HttpClient.urlResultString(url, new ResultCallback<String>() {
            @Override
            public void success(String s) {

            }

            @Override
            public void failure(RuntimeException e) {
                callback.failure(e);
            }
        });
    }
    public static void calllist(String str,ResultCallback resultCallback){

    }

}
