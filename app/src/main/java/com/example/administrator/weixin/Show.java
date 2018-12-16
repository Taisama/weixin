package com.example.administrator.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.weixin.adapter.MoodAdapter;
import com.example.administrator.weixin.model.Pyq;
import com.example.administrator.weixin.thread.GetMoodHttpThread;

import java.util.List;


public class Show extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intentLast = getIntent();
        final Bundle bundle = intentLast.getExtras();
        String uname = bundle.getString("uname");
        GetMoodHttpThread getMoodHttpThread = new GetMoodHttpThread(uname);
        getMoodHttpThread.start();
        try {
            getMoodHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Pyq> list = JSON.parseArray(getMoodHttpThread.getResult(), Pyq.class);
        MoodAdapter adapter = new MoodAdapter(this, R.layout.layout, list);
        ListView listView = (ListView) findViewById(R.id.dtxs);
        listView.setAdapter(adapter);

        ImageView add = (ImageView) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show.this, Write.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        TextView exit=(TextView)findViewById(R.id.pyq);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Show.this,Login.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
