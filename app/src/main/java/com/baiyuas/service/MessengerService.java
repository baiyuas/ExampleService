package com.baiyuas.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * @author: baiyu
 */
public class MessengerService extends Service {


    private Messenger service = new Messenger(new CustomerHandler(msg -> {


    }));


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return service.getBinder();
    }


}
