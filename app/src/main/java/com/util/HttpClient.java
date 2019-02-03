package com.util;
import android.annotation.SuppressLint;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

import javakit.jackson.JacksonUtil;
import javakit.network.HttpClientResponse;

import okhttp3.ResponseBody;

public class HttpClient {
    @SuppressLint("NewApi")
    public static <T> void urlResultFilms(String url,Class<T> clazz,ResultCallback<T> resultCallback){
        HttpClientResponse.String(url, new HttpClientResponse.ResultCallback<String>() {
            @Override
            public void success(String res) throws IOException {
                try {
                    JsonNode result = JacksonUtil.readTree(res).get("result");
                    Shell shell = new Shell();
                    shell.setNumberOfElements(result.get("numberOfElements").asInt());
                    shell.setSize(result.get("size").asInt());
                    shell.setTotalElements(result.get("totalElements").asInt());
                    shell.setTotalPages(result.get("totalPages").asInt());
                    JsonNode content =result.get("content");
                    resultCallback.data(shell,JacksonUtil.json2list(content, clazz));
                } catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void failure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public interface ResultCallback<T>{
        default void data(Shell shell, List<T> json2list){};
    }
    public static class Shell {
        public int number;
        public int numberOfElements;
        public int size;
        public int totalPages;
        public int totalElements;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

    }
}
