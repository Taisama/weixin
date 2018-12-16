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

import com.example.administrator.weixin.thread.RegisterHttpThread;


public class Register extends Activity {
    EditText unameView = null;
    EditText pwdView = null;
    EditText phoneView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button sub = (Button) findViewById(R.id.button3);
        final EditText uname = (EditText) findViewById(R.id.editText3);
        final EditText password = (EditText) findViewById(R.id.editText4);
        final EditText phone = (EditText) findViewById(R.id.editText5);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAll()) {
                    RegisterHttpThread registerHttpThread = new RegisterHttpThread(uname.getText().toString(), password.getText().toString(), phone.getText().toString());
                    registerHttpThread.start();
                    try {
                        registerHttpThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (registerHttpThread.isOk()) {
                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }


    public boolean checkAll() {
        if (checkName() && checkPwd() && checkPhone()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkName() {
        unameView = (EditText) findViewById(R.id.editText3);
        String uname = unameView.getText().toString();
        if (uname.equals("")) {
            Toast.makeText(Register.this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPwd() {
        pwdView = (EditText) findViewById(R.id.editText4);
        String pwd = pwdView.getText().toString();


        if (pwd.equals("")) {
            Toast.makeText(Register.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPhone() {
        phoneView = (EditText) findViewById(R.id.editText5);
        String phone = phoneView.getText().toString();
        if (phone.equals("")) {
            Toast.makeText(Register.this, "号码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
