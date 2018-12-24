package com.simple.proxy;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

public class ProxyBean implements Parcelable {

    private FragmentActivity withActivity;
    private Class<?> toActivity;
    private Intent intent;
    private int requestCode;
    private OnResultListener listener;

    public FragmentActivity getWithActivity() {
        return withActivity;
    }

    public void setWithActivity(FragmentActivity withActivity) {
        this.withActivity = withActivity;
    }

    public Class<?> getToActivity() {
        return toActivity;
    }

    public void setToActivity(Class<?> toActivity) {
        this.toActivity = toActivity;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public OnResultListener getListener() {
        return listener;
    }

    public void setListener(OnResultListener listener) {
        this.listener = listener;
    }

    public ProxyBean() {
    }

    protected ProxyBean(Parcel in) {
        intent = in.readParcelable(Intent.class.getClassLoader());
        requestCode = in.readInt();
    }

    public static final Creator<ProxyBean> CREATOR = new Creator<ProxyBean>() {
        @Override
        public ProxyBean createFromParcel(Parcel in) {
            return new ProxyBean(in);
        }

        @Override
        public ProxyBean[] newArray(int size) {
            return new ProxyBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(intent, flags);
        dest.writeInt(requestCode);
    }
}
