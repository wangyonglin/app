package com.wangyonglin.app.network;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import vendor.json.JacksonUtil;

public class ShellUtil {
    public static void un(String o,String n) throws IOException {
        JsonNode jsonNode = JacksonUtil.readTree(o);
        JsonNode TotalElements = jsonNode.get("result").get("TotalElements");
        JsonNode ContentNode = jsonNode.get("result").get("Content");
        System.out.println(ContentNode.toString());
        if(ContentNode == null){
            throw new NullPointerException("HTTP 请求的内容为空 result->Content");
        }
    }
}
