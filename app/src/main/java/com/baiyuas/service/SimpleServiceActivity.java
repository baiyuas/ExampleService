package com.baiyuas.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * @author bauyu
 */
public class SimpleServiceActivity extends AppCompatActivity {

    private SimpleBindService simpleBindService;
    private SimpleServiceConnection conn = new SimpleServiceConnection();
    private TextView tvCount;
    private boolean hasBindService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_service);
        tvCount = findViewById(R.id.tv_count);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_simple_service:
                startService(new Intent(this, SimpleService.class));
                break;
            case R.id.btn_close_simple_service:
                stopService(new Intent(this, SimpleService.class));
                break;
            case R.id.btn_simple_service_parameter:
                intent = new Intent(this, SimpleService.class);
                intent.putExtra(SimpleService.ARG_SIMPLE, "哈哈，我是参数");
                startService(intent);
                break;
            case R.id.btn_simple_bind_service:
                hasBindService = true;
                bindService(new Intent(this, SimpleBindService.class), conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btn_bind_service_start_count:
                hasBindService = true;
                intent = new Intent(this, SimpleBindService.class);
                intent.putExtra(SimpleBindService.ARG_START_COUNT, true);
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btn_un_bind_simple_service:
                if (hasBindService) {
                    releaseConnection();
                    unbindService(conn);
                    hasBindService = false;
                }
                break;
            default:
        }
    }

    private class SimpleServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof SimpleBindService.SimpleBindImpl) {
                SimpleBindService.SimpleBindImpl bind = (SimpleBindService.SimpleBindImpl) service;
                simpleBindService = bind.getSimpleBindService();
                simpleBindService.setOnCountChangeListener(count -> tvCount.setText(String.valueOf(count)));
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            releaseConnection();
        }
    }


    private void releaseConnection() {
        if (simpleBindService != null) {
            simpleBindService = null;
        }
    }
}
