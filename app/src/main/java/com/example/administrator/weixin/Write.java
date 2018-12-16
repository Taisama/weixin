package com.example.administrator.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.administrator.weixin.thread.PublishHttpThread;


public class Write extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Intent intentLast = getIntent();
        final Bundle bundle = intentLast.getExtras();
        TextView back4 = (TextView) findViewById(R.id.fanhui);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(Write.this, Show.class);
                intent6.putExtras(bundle);
                startActivity(intent6);
            }
        });
        Button button = (Button) findViewById(R.id.publish);
        final EditText editText = (EditText) findViewById(R.id.mood);
        if (editText.getText().toString() != "" && editText.getText().toString() != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    PublishHttpThread publishHttpThread = new PublishHttpThread(bundle.getString("uname"), editText.getText().toString());
                    publishHttpThread.start();
                    try {
                        publishHttpThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write, menu);
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
