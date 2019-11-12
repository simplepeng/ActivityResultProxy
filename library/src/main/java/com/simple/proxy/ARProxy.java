package com.simple.proxy;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ARProxy {

    public static ARProxy with(FragmentActivity activity) {
        return new ARProxy(activity);
    }

    private ProxyBean mBean;

    private ARProxy(FragmentActivity context) {
        mBean = new ProxyBean();
        mBean.setWithActivity(context);
    }

    public ARProxy setIntent(Intent intent){
        mBean.setIntent(intent);
        return this;
    }

    public ARProxy setToActivity(Class<?> clz) {
        mBean.setToActivity(clz);
        return this;
    }

    public ARProxy setRequestCode(int code) {
        mBean.setRequestCode(code);
        return this;
    }

    public void start(OnResultListener listener) {
        mBean.setListener(listener);

        FragmentActivity withActivity = mBean.getWithActivity();
        FragmentManager manager = withActivity.getSupportFragmentManager();
        if (manager == null) return;
        ProxyFragment tag = (ProxyFragment) manager.findFragmentByTag(ProxyFragment.TAG);
        if (tag == null) {
            tag = new ProxyFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bean", mBean);
            tag.setArguments(bundle);
        }

        manager.beginTransaction()
                .add(tag, ProxyFragment.TAG)
                .commitAllowingStateLoss();
        manager.executePendingTransactions();
    }


}
