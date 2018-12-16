package com.example.administrator.weixin.thread;

import android.util.Log;


import com.example.administrator.weixin.util.Http;
import com.example.administrator.weixin.util.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PublishHttpThread extends Thread {
    private String uname;
    private String content;
    private boolean isOk;


    private PublishHttpThread() {
    }

    public PublishHttpThread(String uname, String content) {
        this.uname = uname;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(Http.URL + "getMood?uname=" + uname + "&content=" + content);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(HttpMethod.GET));
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                isOk = stringBuilder.toString().trim().equals("yes");

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