package com.simple.activityresultproxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText etName = findViewById(R.id.et_name);
        EditText etPwd = findViewById(R.id.et_pwd);

        String name = etName.getText().toString();
        String password = etPwd.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "please input name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "please input password", Toast.LENGTH_SHORT).show();
            return;
        }

        UserBean user = new UserBean();
        user.setName(name);
        user.setPassword(password);

        Intent data = new Intent();
        data.putExtra("user", user);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
