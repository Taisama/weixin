package com.example.administrator.weixin.model;

public class Pyq {
    private String headImg;
    private String uname;
    private String mood;

    public Pyq() {
    }

    public Pyq(String headImg, String uname, String mood) {
        this.headImg = headImg;
        this.uname = uname;
        this.mood = mood;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}