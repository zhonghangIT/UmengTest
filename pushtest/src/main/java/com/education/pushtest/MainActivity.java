package com.education.pushtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.message.IUmengCallback;

public class MainActivity extends BaseActivity {
    private Button mBntStartPush;
    private Button mBntStopPush;
    private Button mBntStartDisturb;
    private Button mBntStopDisturb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBntStartPush = (Button) findViewById(R.id.btn_start_push);
        mBntStopPush = (Button) findViewById(R.id.btn_stop_push);
        mBntStartDisturb = (Button) findViewById(R.id.btn_start_disturb);
        mBntStopDisturb = (Button) findViewById(R.id.btn_stop_disturb);
        mBntStartPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启推送
                ((App)getApplication()).mPushAgent.enable(new IUmengCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(String s, String s1) {

                    }
                });
            }
        });
        mBntStopPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭推送
                ((App)getApplication()).mPushAgent.disable(new IUmengCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(String s, String s1) {

                    }
                });
            }
        });
        mBntStartDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启免打扰模式,四个参数开始的时 开始的分 关闭的时 关闭的分
                ((App)getApplication()).mPushAgent.setNoDisturbMode(23, 0, 7, 0);
            }
        });
        mBntStopDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplication()).mPushAgent.setNoDisturbMode(0, 0, 0, 0);
            }
        });
    }
}
