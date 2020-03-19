package com.simple.activityresultproxy;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRequestCode = findViewById(R.id.tv_requestCode);
        mTvResultCode = findViewById(R.id.tv_resuleCode);
        mTvData = findViewById(R.id.tv_data);
        mTvUser = findViewById(R.id.tv_user);
    }

    public void go(View view) {
        finish();

        Intent intent = new Intent(MainActivity.this, ToActivity.class);
        intent.putExtra("name", "simple");

        ARProxy.with(MainActivity.this)
                .navTo(ToActivity.class)
                .putExtra("name","simple")
                .putExtra("age",26)
                .putExtra("man",true)
                .getResult(REQUEST_CODE, new OnResultListener() {
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


    public void login(View view) {
        LoginHelper.isLogin(MainActivity.this, new LoginHelper.OnLoginListener() {
            @Override
            public void onLogin(UserBean user) {
                mTvUser.setText(String.format("user : %s - %s", user.getName(), user.getPassword()));
            }
        });
    }

}
