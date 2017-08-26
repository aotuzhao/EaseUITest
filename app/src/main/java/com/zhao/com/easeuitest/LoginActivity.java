package com.zhao.com.easeuitest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by zhao on 2017/8/26.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText user_name;
    private EditText user_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name= (EditText) findViewById(R.id.user_name);
        user_password= (EditText) findViewById(R.id.user_password);

        findViewById(R.id.user_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        findViewById(R.id.user_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });
    }


    private void userRegister(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(user_name.getText().toString().trim(),user_password.getText().toString().trim());
                    Log.e("环信--注册成功","");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("环信--注册失败",e.getErrorCode()+", "+e.getMessage());
                }
            }
        }).start();
    }
    private void userLogin(){
        EMClient.getInstance().login(user_name.getText().toString().trim(), user_password.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                Log.e("环信--登录","登录成功");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("环信--登陆",i +", "+s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
