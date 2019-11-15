package com.simple.proxy;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.io.Serializable;
import java.util.ArrayList;

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
        if (mIntent.getComponent() == null) {
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

    //31 methods
    public ARProxy putExtra(String name, boolean value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, byte value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, char value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, short value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, int value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, long value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, float value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, double value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, String value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, CharSequence value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, Parcelable value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, Parcelable[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putParcelableArrayListExtra(String name, ArrayList<? extends Parcelable> value) {
        mIntent.putParcelableArrayListExtra(name, value);
        return this;
    }

    public ARProxy putIntegerArrayListExtra(String name, ArrayList<Integer> value) {
        mIntent.putIntegerArrayListExtra(name, value);
        return this;
    }

    public ARProxy putStringArrayListExtra(String name, ArrayList<String> value) {
        mIntent.putStringArrayListExtra(name, value);
        return this;
    }

    public ARProxy putCharSequenceArrayListExtra(String name, ArrayList<CharSequence> value) {
        mIntent.putCharSequenceArrayListExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, Serializable value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, boolean[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, byte[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, short[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, char[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, int[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, long[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, float[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, double[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, String[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, CharSequence[] value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtra(String name, Bundle value) {
        mIntent.putExtra(name, value);
        return this;
    }

    public ARProxy putExtras(@NonNull Intent src) {
        mIntent.putExtras(src);
        return this;
    }

    public ARProxy putExtras(@NonNull Bundle extras) {
        mIntent.putExtras(extras);
        return this;
    }
}
