package com.simple.activityresultproxy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.simple.proxy.ARProxy;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 110;
    private final int REQUEST_CODE_CHOICE_IMAGE = 111;

    private TextView mTvRequestCode;
    private TextView mTvResultCode;
    private TextView mTvData;
    private TextView mTvUser;
    private ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRequestCode = findViewById(R.id.tv_requestCode);
        mTvResultCode = findViewById(R.id.tv_resuleCode);
        mTvData = findViewById(R.id.tv_data);
        mTvUser = findViewById(R.id.tv_user);
        mIvImage = findViewById(R.id.ivImage);
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
                    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                        if (data == null) return;
                        mTvRequestCode.setText(String.format("requestCode : %s", requestCode));
                        mTvResultCode.setText(String.format("resultCode  :%s", resultCode));

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

    public void choiceImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "choice image"), REQUEST_CODE_CHOICE_IMAGE);

        ARProxy.navTo(this, intent)
//                .startActivity();
                .startActivityForResult(REQUEST_CODE_CHOICE_IMAGE, new ARProxy.OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                        if (requestCode == REQUEST_CODE_CHOICE_IMAGE && resultCode == RESULT_OK
                                && data != null && data.getData() != null) {
                            Uri uri = data.getData();
                            mIvImage.setImageURI(uri);
                        }
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

        if (requestCode == REQUEST_CODE_CHOICE_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri uri = data.getData();
            mIvImage.setImageURI(uri);
        }
    }
}
