package com.simple.proxy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class ARProxy {

    public static ARProxy with(FragmentActivity activity) {
        return new ARProxy(activity);
    }

    //想了想还是只支持FragmentActivity算了
//    public static ARProxy with(Activity activity) {
//        return new ARProxy(activity);
//    }

    private ProxyBean mBean;

    private ARProxy(FragmentActivity context) {
        mBean = new ProxyBean();
        mBean.setWithActivity(context);
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
