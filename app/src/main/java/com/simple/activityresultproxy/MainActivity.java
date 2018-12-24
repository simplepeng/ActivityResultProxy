package com.simple.activityresultproxy;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.simple.proxy.ARProxy;
import com.simple.proxy.OnResultListener;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 110;

    private TextView mTvRequestCode;
    private TextView mTvResultCode;
    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRequestCode = findViewById(R.id.tv_requestCode);
        mTvResultCode = findViewById(R.id.tv_resuleCode);
        mTvData = findViewById(R.id.tv_data);
    }

    public void go(View view) {
        ARProxy.with(MainActivity.this)
                .setToActivity(ToActivity.class)
                .setRequestCode(REQUEST_CODE)
                .start(new OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {

                        if (requestCode != REQUEST_CODE || resultCode != Activity.RESULT_OK ||
                                data == null) return;
                        mTvRequestCode.setText(String.format("requestCode : %s", requestCode));
                        mTvResultCode.setText(String.format("resultCode  :%s", resultCode));

                        Bundle extras = data.getExtras();
                        mTvData.setText(String.format("data : %s-%s", extras.getString("username"),
                                extras.getBoolean("isLogin")));

                    }
                });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
}
