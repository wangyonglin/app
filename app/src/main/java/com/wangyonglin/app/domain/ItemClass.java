package com.wangyonglin.app.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ItemClass implements Parcelable {
    public String listid;
    public String download;
    public String overview;
    public String name;
    public String picture;
    public String image;
    public String released;
    public ItemClass(){}


    protected ItemClass(Parcel in) {
        listid = in.readString();
        download = in.readString();
        overview = in.readString();
        name = in.readString();
        picture = in.readString();
        image = in.readString();
        released = in.readString();
    }

    public static final Creator<ItemClass> CREATOR = new Creator<ItemClass>() {
        @Override
        public ItemClass createFromParcel(Parcel in) {
            return new ItemClass(in);
        }

        @Override
        public ItemClass[] newArray(int size) {
            return new ItemClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(listid);
        parcel.writeString(download);
        parcel.writeString(overview);
        parcel.writeString(name);
        parcel.writeString(picture);
        parcel.writeString(image);
        parcel.writeString(released);
    }
}
