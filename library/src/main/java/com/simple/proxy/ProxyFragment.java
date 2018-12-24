package com.simple.proxy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class ProxyFragment extends Fragment {

    public static final String TAG = "ProxyFragment";
    private ProxyBean bean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        bean = bundle.getParcelable("bean");
        if (bean == null) return;

        if (bean.getIntent() != null) {
            startActivityForResult(bean.getIntent(), bean.getRequestCode());
            return;
        }

        if (bean.getToActivity() != null) {
            Intent intent = new Intent(context, bean.getToActivity());
            startActivityForResult(intent, bean.getRequestCode());
        } else {
            throw new NullPointerException("to Activity is null");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (bean != null && bean.getListener() != null) {
            bean.getListener().onActivityResult(requestCode, resultCode, data);
        }

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
