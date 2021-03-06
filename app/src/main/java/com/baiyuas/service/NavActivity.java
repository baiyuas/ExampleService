package com.baiyuas.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author baiyu
 */
public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, SimpleServiceActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, MessengerServiceActivity.class));
                break;
            default:
        }
    }
}
