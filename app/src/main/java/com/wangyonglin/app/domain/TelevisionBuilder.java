package com.wangyonglin.app.domain;

import java.util.List;

public class TelevisionBuilder {
    public String title;
    public String cover;
    public String uuid;
    public List<ItemClass> itemClasses;
    public static TelevisionBuilder create(String title,String cover,String uuid,List<ItemClass> itemClasses){
        TelevisionBuilder builder = new TelevisionBuilder();
        builder.title=title;
        builder.cover=cover;
        builder.uuid=uuid;
        builder.itemClasses=itemClasses;
        return builder;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ItemClass> getItemClasses() {
        return itemClasses;
    }

    public void setItemClasses(List<ItemClass> itemClasses) {
        this.itemClasses = itemClasses;
    }
}
