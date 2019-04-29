package com.khbd.data;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class FileUtils {
    public static void clearFileData(Context context,String filename){
        try {
            FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);
            byte[]  bytes = null;

            fos.write(bytes);//将byte数组写入文件

            fos.close();//关闭文件输出流

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //向指定的文件中写入指定的数据
    public static void writeFileData(Context context,String filename, String data){

        try {

            FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);//获得FileOutputStream

            //将要写入的字符串转换为byte数组

            byte[]  bytes = data.getBytes();

            fos.write(bytes);//将byte数组写入文件

            fos.close();//关闭文件输出流

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打开指定文件，读取其数据，返回字符串对象
    public static String readFileData(Context context,String fileName){

        String result="";

        try{

            FileInputStream fis = context.openFileInput(fileName);

            //获取文件长度
            int lenght = fis.available();

            byte[] buffer = new byte[lenght];

            fis.read(buffer);

            //将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }

    //打开指定文件，读取其数据，返回字符串对象
    public static String readFileDataEx(Context context,String fileName) throws FileNotFoundException{
        String result="";
        try{
            FileInputStream fis = context.openFileInput(fileName);
            //获取文件长度
            int lenght = fis.available();
            byte[] buffer = new byte[lenght];
            fis.read(buffer);
            //将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }
}
