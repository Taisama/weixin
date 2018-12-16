package com.example.administrator.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.weixin.thread.LoginHttpThread;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, Register.class);
                startActivity(intent2);
            }
        });
        Button login = (Button) findViewById(R.id.button);
        final EditText uname = (EditText) findViewById(R.id.editText);
        final EditText pwd = (EditText) findViewById(R.id.editText2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin()) {
                    return;
                }
                LoginHttpThread loginHttpThread = new LoginHttpThread(uname.getText().toString(), pwd.getText().toString());
                loginHttpThread.start();
                try {
                    loginHttpThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean success = loginHttpThread.isOk();
                if (success) {
                    Bundle bundle = new Bundle();
                    Intent intent3 = new Intent(Login.this, Show.class);
                    bundle.putString("uname", uname.getText().toString());
                    intent3.putExtras(bundle);
                    startActivity(intent3);
                } else {
                    Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean checkLogin() {
        EditText e1 = (EditText) findViewById(R.id.editText);
        EditText e2 = (EditText) findViewById(R.id.editText2);
        if (e1.getText().toString().equals("")) {
            Toast.makeText(Login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (e2.getText().toString().equals("")) {
                Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
