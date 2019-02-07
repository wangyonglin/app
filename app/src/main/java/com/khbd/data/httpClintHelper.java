package com.khbd.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.data.RecyclerData;
import com.factory.LoadingFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kernel.Loading;
import com.khbd.app.MainActivity;
import com.util.JSONUtil;
import com.util.ToastUtil;
import com.vendor.design.Atom;
import com.wangyonglin.app.network.Video;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javakit.jackson.JacksonUtil;
import javakit.network.HttpClientResponse;
import javakit.result.ResultCallback;

public class httpClintHelper {
    public static void ResultAtoms(@NonNull String url,javakit.result.ResultCallback<List<Atom>> resultCallback) {
         Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0x001){
                    resultCallback.resove((List<Atom>)msg.obj);
                }
            }
        };
        Message msg= new Message();
        msg.what=0x001;
        httpClient(url, new ResultCallback<List<Atom>>() {
            @Override
            public void resove(List<Atom> cls) {
                msg.obj=cls;
                handler.sendMessage(msg);
            }
        });
    }
    @SuppressLint("NewApi")
    private static void httpClient(String url, javakit.result.ResultCallback<List<Atom>> resultCallback){
        List<Atom> atoms =new ArrayList<Atom>() ;
        HttpClientResponse.String(url, new HttpClientResponse.ResultCallback<String>() {
            @Override
            public void success(String res)  {
                // TODO Auto-generated method stub
                if(res.isEmpty()){
                    resultCallback.reject(new Exception("http request error empty respones"));
                    return;
                }
                try {
                    JsonNode result = JacksonUtil.readTree(res).get("result");
                    JSONUtil.Shell shell = new JSONUtil.Shell();
                    shell.setNumberOfElements(result.get("numberOfElements").asInt());
                    shell.setSize(result.get("size").asInt());
                    shell.setTotalElements(result.get("totalElements").asInt());
                    shell.setTotalPages(result.get("totalPages").asInt());
                    JsonNode content =result.get("content");
                    List<com.wangyonglin.app.network.Video> list = JacksonUtil.json2list(content,Video.class);
                    for( int i = 0 ; i < list.size() ; i++) {
                        atoms.add( new Atom(list.get(i).getTitle(),list.get(i).getVideo(),list.get(i).getImage(),list.get(i).getUuid()));
                    }
                    resultCallback.resove(atoms);

                } catch (JsonParseException e) {
                    resultCallback.reject(e);
                } catch (JsonMappingException e) {
                    resultCallback.reject(e);
                } catch (IOException e) {
                    resultCallback.reject(e);
                }
            }

            @Override
            public void failure(Exception e) {
                resultCallback.reject(e);
            }
        });
    }

}
