package com.example.administrator.weixin.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.administrator.weixin.util.Http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HeadImageHttpThread extends Thread {
    private String imageUrl;
    private Bitmap resultBitmap;

    public HeadImageHttpThread(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(Http.URL + imageUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            setResultBitmap(BitmapFactory.decodeStream(inputStream));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getResultBitmap() {
        return resultBitmap;
    }

    public void setResultBitmap(Bitmap resultBitmap) {
        this.resultBitmap = resultBitmap;
    }
}
