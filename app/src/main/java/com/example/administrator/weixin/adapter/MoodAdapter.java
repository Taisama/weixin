package com.example.administrator.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.weixin.R;
import com.example.administrator.weixin.model.Pyq;
import com.example.administrator.weixin.thread.HeadImageHttpThread;

import java.util.List;

public class MoodAdapter extends ArrayAdapter {
    private int resourceId;

    public MoodAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Pyq pyqItem = (Pyq) getItem(position);
        LayoutView layoutView = new LayoutView();
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            layoutView.imageView = (ImageView) view.findViewById(R.id.headImage);
            layoutView.textView = (TextView) view.findViewById(R.id.unamePyq);
            layoutView.textView2 = (TextView) view.findViewById(R.id.moods);
            view.setTag(layoutView);
        } else {
            view = convertView;
            layoutView = (LayoutView) view.getTag();
        }
        HeadImageHttpThread headImgHttpThread = new HeadImageHttpThread(pyqItem.getHeadImg());
        headImgHttpThread.start();
        try {
            headImgHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layoutView.imageView.setImageBitmap(headImgHttpThread.getResultBitmap());
        layoutView.textView.setText(pyqItem.getUname());
        layoutView.textView2.setText(pyqItem.getMood());
        return view;
    }

    private class LayoutView {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
    }
}