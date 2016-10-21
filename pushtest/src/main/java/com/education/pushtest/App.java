package com.education.pushtest;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by zhonghang on 2016/10/21.
 */

public class App extends Application {
    //因为要在别的界面关闭推送和开启推送所以当成全局变量
    public PushAgent mPushAgent;
    @Override
    public void onCreate() {
        super.onCreate();
        initPush();
    }

    private void initPush() {
        //初始化推送服务
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("token", "token:########" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("token", "token:########");
            }
        });
        initNotificationClick(mPushAgent);
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                                getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

    }

    private void initNotificationClick(PushAgent mPushAgent) {
        //自定义通知栏点击事件
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                Log.d("notification","----------------------------------------");
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}
