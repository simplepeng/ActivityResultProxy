package com.simple.activityresultproxy;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.simple.proxy.ARProxy;

public class LoginHelper {

    private static final int LOGIN_REQUEST_CODE = 120;
    private static boolean isLogin = false;//模拟未登录情况

    public static void isLogin(FragmentActivity activity, final OnLoginListener listener) {
        if (isLogin) {
            //模拟已经登录过，真实情况应该是从sp或者db中获取用户信息
            UserBean user = new UserBean();
            user.setName("isLogin - simple");
            user.setPassword("123456");
            listener.onLogin(user);
            return;
        }

        ARProxy.navTo(activity, LoginActivity.class)
                .startActivityForResult(LOGIN_REQUEST_CODE, new ARProxy.OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                        if (data == null) return;

                        UserBean user = data.getParcelableExtra("user");
                        if (user == null) return;
                        isLogin = true;
                        listener.onLogin(user);
                    }
                });
    }

    public interface OnLoginListener {
        void onLogin(UserBean user);
    }
}
