package com.baiyuas.service;

import android.os.Handler;
import android.os.Message;

/**
 * @author: baiyu
 */
public class CustomerHandler extends Handler {

    private CustomerHandler.Callback callback;

    private CustomerHandler() {
    }

    public CustomerHandler(CustomerHandler.Callback callback) {
    }

    @Override
    public void handleMessage(Message msg) {
        callback.handleMessage(msg);
    }


    public interface Callback {
        void handleMessage(Message msg);
    }
}
