package com.vendor.design;

public class Atom {
    private String Title;
    private String Video;
    private String Poster;
    private String UUID;

    public Atom(String title, String video, String poster, String UUID) {
        Title = title;
        Video = video;
        Poster = poster;
        this.UUID = UUID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
