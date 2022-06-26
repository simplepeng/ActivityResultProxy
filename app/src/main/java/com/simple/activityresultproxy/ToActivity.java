package com.simple.activityresultproxy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);

        TextView tv_args = findViewById(R.id.tv_args);
        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);
        boolean man = getIntent().getBooleanExtra("man", false);

        tv_args.setText(name + "----" + age + "----" + man);
    }

    public void back(View view) {
        Intent data = new Intent();
        data.putExtra("username", "simple peng");
        data.putExtra("isLogin", true);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ToActivity", "ToActivity onDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d("ToActivity", "ToActivity finalize");
    }
}
