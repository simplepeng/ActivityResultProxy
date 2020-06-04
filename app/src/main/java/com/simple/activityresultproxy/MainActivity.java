package com.simple.activityresultproxy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.simple.proxy.ARProxy;

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
//        finish();

        Intent intent = new Intent(MainActivity.this, ToActivity.class);
        intent.putExtra("name", "simple");
//        startActivityForResult();

//        new ARProxy(this,ToActivity.class)
        ARProxy.navTo(this, ToActivity.class)
                .putExtra("name", "simple")
                .putExtra("age", 26)
                .putExtra("man", true)
                .startActivityForResult(REQUEST_CODE, new ARProxy.OnResultListener() {
                    @Override
                    public void onActivityResult(@Nullable Intent data) {
                        if (data == null) return;
//                        mTvRequestCode.setText(String.format("requestCode : %s", requestCode));
//                        mTvResultCode.setText(String.format("resultCode  :%s", resultCode));

                        Bundle extras = data.getExtras();
                        mTvData.setText(String.format("data : %s-%s", extras.getString("username"),
                                extras.getBoolean("isLogin")));
                    }
                });
//                .startActivity();
    }


    public void login(View view) {
        LoginHelper.isLogin(MainActivity.this, new LoginHelper.OnLoginListener() {
            @Override
            public void onLogin(UserBean user) {
                mTvUser.setText(String.format("user : %s - %s", user.getName(), user.getPassword()));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "requestCode = " + requestCode + "--resultCode = " + resultCode);
        if (data != null && data.getExtras() != null) {
            Log.d("onActivityResult", "data = " + data.getExtras().toString());
        }
    }
}
