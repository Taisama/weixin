package com.example.administrator.weixin.thread;

import java.net.URL;


import com.example.administrator.weixin.util.Http;
import com.example.administrator.weixin.util.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginHttpThread extends Thread {
    private String uname;
    private String pwd;
    private boolean isOk;

    public LoginHttpThread() {
        this.isOk = false;
    }

    public LoginHttpThread(String uname, String pwd) {
        this.isOk = false;
        this.uname = uname;
        this.pwd = pwd;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(Http.URL + "/login?uname=" + uname + "&pwd=" + pwd);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp;
                StringBuffer stringBuffer = new StringBuffer();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(temp);
                }
                isOk = stringBuffer.toString().trim().equals("yes");
            } else {
                isOk = false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isOk() {
        return isOk;
    }
}
