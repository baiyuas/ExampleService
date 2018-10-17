package com.baiyuas.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author baiyu
 */
public class MessengerServiceActivity extends AppCompatActivity {

    private Messenger client;
    private boolean isBindedService;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            client = new Messenger(service);
            isBindedService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            client = null;
            isBindedService = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_service);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                bindService(new Intent(this, MessengerService.class), conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btn_close_service:

                break;
            case R.id.btn_send_msg_to_service:

                break;
                
            default:
        }
    }
}
