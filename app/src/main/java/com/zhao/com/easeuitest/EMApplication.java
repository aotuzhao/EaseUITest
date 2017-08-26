package com.zhao.com.easeuitest;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by zhao on 2017/8/26.
 */

public class EMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EaseUI.getInstance().init(this,null);
        EMClient.getInstance().setDebugMode(true);

    }
}
