package com.example.administrator.weixin.model;

public class User {
    private String headImg;
    private String uname;
    private String pwd;
    private String phone;

    public User() {
    }

    public User(String headImg, String uname, String pwd, String phone) {
        this.headImg = headImg;
        this.uname = uname;
        this.pwd = pwd;
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setName(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return uname + pwd + phone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}