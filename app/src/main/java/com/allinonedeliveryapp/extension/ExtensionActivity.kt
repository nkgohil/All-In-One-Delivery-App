package com.allinonedeliveryapp.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.util.ProgressDialogUtils

/*---------- Toast ----------*/
fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}

/*---------- Progress Dialog ----------*/
fun AppCompatActivity.showProgressDialog() {
    showProgressDialog("Loading...")
}

fun AppCompatActivity.showProgressDialog(message: String) {
    ProgressDialogUtils.getInstance()
        .create(this)
        .withMessage(message)
        .cancelable(false)
        .show()
}

fun AppCompatActivity.hideProgressDialog() {
    ProgressDialogUtils.getInstance()
        .hide()
}