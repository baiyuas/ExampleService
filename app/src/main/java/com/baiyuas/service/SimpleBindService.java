package com.baiyuas.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: baiyu
 */
public class SimpleBindService extends Service {

    public static final String ARG_START_COUNT = "ARG_START_COUNT";
    private int count = 0;
    private SimpleBindImpl bindImpl = new SimpleBindImpl();
    private ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(2);
    private CountChangeListener countChangeListener;

    public void setOnCountChangeListener(CountChangeListener countChangeListener) {
        this.countChangeListener = countChangeListener;
    }

    private void log(String content) {
        Log.d(SimpleBindService.class.getSimpleName(), content);
    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public class SimpleBindImpl extends Binder {

        public SimpleBindService getSimpleBindService() {
            return SimpleBindService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("===================onBind");
        boolean isStartCount = intent.getBooleanExtra(ARG_START_COUNT, false);
        if (isStartCount) {
            pool.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    log("------start timer:" + count);
                    count++;
                    MainThreadTool.executeOnMainThread(() -> {
                        if (countChangeListener != null) {
                            countChangeListener.change(count);
                        }
                    });
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
        return bindImpl;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (pool != null) {
            pool.shutdownNow();
        }
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
        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log("------start timer:" + count);
                count++;
            }
        }, 0, 1, TimeUnit.SECONDS);
        log("===================onStartCommand, flags:" + flags + ",startId:" + startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        log("===================onDestroy");
        super.onDestroy();
    }

    public interface CountChangeListener {
        /**
         *  计数回调
         * @param count
         */
        void change(int count);
    }
}
