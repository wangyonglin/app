package com.vendor.domain;

public class Item {
    private String url;
    private String name;
    private String uuid;

    public Item() {
    }

    public Item(String url, String name, String uuid) {
        this.url = url;
        this.name = name;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
