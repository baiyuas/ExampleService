package com.baiyuas.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Copyright (c)2017
 * 欣网互联网络科技有限公司
 *
 * @author: lpc
 * @description:
 */
public class SimpleService extends Service {

    public static final String ARG_SIMPLE = "ARG_SIMPLE";
    private int count = 0;


    private void log(String content) {
        Log.d(SimpleService.class.getSimpleName(), content);
    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("===================onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("===================onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        log("===================onCreate");
        super.onCreate();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                log("------start timer:" + count);
                count++;
            }
        }, 0, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String receivedParameter = intent.getStringExtra(ARG_SIMPLE);
        toast(receivedParameter);
        log("===================onStartCommand, flags:" + flags + ",startId:" + startId);
        return START_NOT_STICKY ;
    }

    @Override
    public void onDestroy() {
        log("===================onDestroy");
        super.onDestroy();
    }
}
