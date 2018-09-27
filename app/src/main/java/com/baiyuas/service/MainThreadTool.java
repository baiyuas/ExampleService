package com.baiyuas.service;


import android.os.Handler;
import android.os.Looper;

/**
 * @author lpc
 * @description
 */
public class MainThreadTool {

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void executeOnMainThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
