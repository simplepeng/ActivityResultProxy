package com.simple.proxy

import android.content.Context
import android.content.Intent

fun Context.startActivityForResult(
    requestCode: Int,
    intent: Intent,
    onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
) {
    ARProxy.navTo(this, intent)
        .startActivityForResult(requestCode, onActivityResult)
}

fun Context.startActivityForResult(
    requestCode: Int,
    clazz: Class<*>,
    onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
) {
    ARProxy.navTo(this, clazz)
        .startActivityForResult(requestCode, onActivityResult)
}

fun Context.navTo(
    clazz: Class<*>
): ARProxy {
    return ARProxy.navTo(this, clazz)
}

fun Context.navTo(
    intent: Intent,
): ARProxy {
    return ARProxy.navTo(this, intent)
}

fun ARProxy.startActivityForResult(
    requestCode: Int,
    onActivityResult: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
) {
    this.startActivityForResult(requestCode, onActivityResult)
}