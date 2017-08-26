package com.zhao.com.easeuitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

public class MainActivity extends AppCompatActivity {

    private EditText chat_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat_name= (EditText) findViewById(R.id.chat_user_name);

        findViewById(R.id.chat_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChat();
            }
        });

        findViewById(R.id.user_login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void startChat(){
        Intent intent=new Intent(MainActivity.this,ChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_USER_ID, chat_name.getText().toString().trim());
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
        startActivity(intent);
    }

    private void logout(){
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                Log.e("环信--注册","退出成功");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("环信--退出登录",i+", "+s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
