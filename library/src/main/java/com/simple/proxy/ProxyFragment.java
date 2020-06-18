package com.simple.proxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProxyFragment extends Fragment {

    static final String TAG = "ProxyFragment";

    private ARProxy.OnResultListener mOnResultListener;
    private int mRequestCode = -1;

    public ProxyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(int requestCode, Intent intent, ARProxy.OnResultListener listener) {
        this.mOnResultListener = listener;
        this.mRequestCode = requestCode;
        this.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mOnResultListener != null && requestCode == mRequestCode
                && resultCode == Activity.RESULT_OK) {
            mOnResultListener.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
