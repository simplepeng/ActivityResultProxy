package com.simple.proxy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ProxyFragment extends Fragment {

    public static final String TAG = "ProxyFragment";

    private Intent mIntent;
    private int mRequestCode;
    private OnResultListener mOnResultListener;

    public ProxyFragment(Intent intent, int requestCode, OnResultListener listener) {
        this.mIntent = intent;
        this.mRequestCode = requestCode;
        this.mOnResultListener = listener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        startActivityForResult(mIntent, mRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mOnResultListener.onActivityResult(requestCode, resultCode, data);

        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            Fragment tag = manager.findFragmentByTag(TAG);
            if (tag != null) {
                manager.beginTransaction()
                        .remove(tag)
                        .commit();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
