package com.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

import javakit.jackson.JacksonUtil;

public class JSONUtil {
    public static <T> void CALL(String data,final Class<T> clazz,final ShellResultCallback<T> res)  throws IOException{

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
    }



    public interface ShellResultCallback<T>{
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
