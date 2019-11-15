package com.simple.proxy;

import android.content.ComponentName;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ARProxy {

    private FragmentActivity mActivity;
    private Intent mIntent;

    private ARProxy(FragmentActivity activity) {
        mActivity = activity;
        mIntent = new Intent();
    }

    public static ARProxy with(FragmentActivity activity) {
        return new ARProxy(activity);
    }

    public ARProxy navTo(Class<?> cls) {
        mIntent.setComponent(new ComponentName(mActivity, cls));
        return this;
    }

    public void getResult(int requestCode, OnResultListener listener) {
        if (mIntent.getComponent() == null){
            throw new NullPointerException("navTo method no call");
        }

        final FragmentActivity withActivity = mActivity;
        FragmentManager manager = withActivity.getSupportFragmentManager();
        if (manager == null) return;

        ProxyFragment fragment = new ProxyFragment(mIntent, requestCode, listener);

        manager.beginTransaction()
                .add(fragment, ProxyFragment.TAG)
                .commitAllowingStateLoss();
        manager.executePendingTransactions();
    }


}
