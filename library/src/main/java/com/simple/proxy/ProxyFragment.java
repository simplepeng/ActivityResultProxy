package com.simple.proxy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProxyFragment extends Fragment {

    static final String TAG = "ProxyFragment";

    public ProxyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(int requestCode, Intent intent) {
        this.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ARProxy.OnResultListener listener = ARProxy.mListenerMap.get(requestCode);
        if (listener != null) {
            listener.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
