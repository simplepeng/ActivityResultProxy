package com.simple.proxy;

import android.content.Intent;
import androidx.annotation.Nullable;

public interface OnResultListener {
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
