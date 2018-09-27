package com.baiyuas.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author bauyu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_service:
                startService(new Intent(this, SimpleService.class));
                break;
            case R.id.btn_close_simple_service:
                stopService(new Intent(this, SimpleService.class));
                break;
            case R.id.btn_simple_service_parameter:
                Intent intent = new Intent(this, SimpleService.class);
                intent.putExtra(SimpleService.ARG_SIMPLE, "哈哈，我是参数");
                startService(intent);
                break;
            default:
        }
    }
}
