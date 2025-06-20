package com.simple.proxy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.io.Serializable;
import java.util.ArrayList;

public class ARProxy {

    private FragmentActivity mActivity;
    private Intent mIntent;

    public static final SparseArray<ARProxy.OnResultListener> mListenerMap = new SparseArray<>();

    private ARProxy(Context context, Class<?> clazz) {
        this.mActivity = ((FragmentActivity) context);
        this.mIntent = new Intent(context, clazz);
    }

    private ARProxy(Context context, Intent intent) {
        this.mActivity = ((FragmentActivity) context);
        this.mIntent = intent;
    }

    public static ARProxy navTo(Context context, Class<?> clazz) {
        if (!(context instanceof FragmentActivity)) {
            throw new IllegalArgumentException("context must be extends FragmentActivity");
        }
        return new ARProxy(context, clazz);
    }

    public static ARProxy navTo(Context context, Intent intent) {
        if (!(context instanceof FragmentActivity)) {
            throw new IllegalArgumentException("context must be extends FragmentActivity");
        }
        return new ARProxy(context, intent);
    }

    public void startActivity() {
        if (mActivity == null) return;

        mActivity.startActivity(mIntent);
    }

    public void startActivityForResult(int requestCode, ARProxy.OnResultListener listener) {
        if (listener == null) {
            throw new NullPointerException("OnResultListener can not be null");
        }

//        if (mIntent.getComponent() == null) {
//            throw new NullPointerException("navTo method is not called");
//        }

        if (mActivity == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (mActivity.isDestroyed()) return;
        }
        if (mActivity.isFinishing()) return;

        FragmentManager manager = mActivity.getSupportFragmentManager();

        ProxyFragment fragment;
        Fragment tagFragment = manager.findFragmentByTag(ProxyFragment.TAG);
        if (tagFragment == null) {
            fragment = new ProxyFragment();
            manager.beginTransaction()
                    .add(fragment, ProxyFragment.TAG)
                    .commitNowAllowingStateLoss();
//                    .commitNow();
        } else {
            fragment = ((ProxyFragment) tagFragment);
        }

        mListenerMap.put(requestCode, listener);
        fragment.startActivityForResult(requestCode, mIntent);
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

    public interface OnResultListener {
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
