package com.baiyuas.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: lpc
 * @description:
 */
public class SimpleService extends Service {

    public static final String ARG_SIMPLE = "ARG_SIMPLE";
    private int count = 0;
    private ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(2);
    private ScheduledFuture scheduledFuture;

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String receivedParameter = intent.getStringExtra(ARG_SIMPLE);
        toast(receivedParameter);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log("------start timer:" + count);
                count++;
            }
        },0, 1, TimeUnit.SECONDS);
        log("===================onStartCommand, flags:" + flags + ",startId:" + startId);
        return START_NOT_STICKY ;
    }

    @Override
    public void onDestroy() {
        log("===================onDestroy");
        if (pool != null) {
            pool.shutdownNow();
        }
        super.onDestroy();
    }
}
