package com.util;

import android.os.Environment;

import java.io.File;

import static android.os.Environment.DIRECTORY_ALARMS;
import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.DIRECTORY_NOTIFICATIONS;
import static android.os.Environment.DIRECTORY_PODCASTS;
import static android.os.Environment.DIRECTORY_RINGTONES;

public class StorageUtil {
    public static File DATA(){
        return Environment.getDataDirectory();
    }
    public static File CACHE(){
        return Environment.getDownloadCacheDirectory();
    }
    public static File SYSTEM(){
        return Environment.getRootDirectory();
    }
    public static File ALARMS(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_ALARMS);
    }
    public static File DCIM(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
    }
    public static File DOWNLOADS(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
    }
    public static File MOVIES(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES);
    }
    public static File MUSIC(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC);
    }
    public static File NOTIFICATIONS(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_NOTIFICATIONS);
    }
    public static File PODCASTS(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_PODCASTS);
    }
    public static File RINGTONES(){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES);
    }
}
