package com.education.pushtest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.message.PushAgent;

/**
 * Created by zhonghang on 2016/10/21.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        PushAgent.getInstance(this).onAppStart();
    }
}
