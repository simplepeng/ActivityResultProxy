package com.simple.activityresultproxy;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class ToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
    }

    public void back(View view) {
        Intent data = new Intent();
        data.putExtra("username", "simple peng");
        data.putExtra("isLogin", true);
        setResult(RESULT_OK, data);
        finish();
    }
}
