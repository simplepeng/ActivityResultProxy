package com.simple.proxy

import android.content.Context
import android.content.Intent

fun Context.startActivityForResult(
    requestCode: Int,
    intent: Intent,
    onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
) {
    ARProxy.navTo(this, intent)
        .startActivityForResult(requestCode) { requestCode, resultCode, data ->
            onActivityResult.invoke(requestCode, resultCode, data)
        }
}

fun Context.startActivityForResult(
    requestCode: Int,
    clazz: Class<*>,
    onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
) {
    ARProxy.navTo(this, clazz)
        .startActivityForResult(requestCode) { requestCode, resultCode, data ->
            onActivityResult.invoke(requestCode, resultCode, data)
        }
}