package com.jamangle.utils

import android.app.ProgressDialog
import android.content.Context

class ProgressDialogUtils() {

    private var progressDialog: ProgressDialog? = null

    companion object {
        private var instance: ProgressDialogUtils? = null

        fun getInstance(): ProgressDialogUtils {
            if (instance == null) {
                instance = ProgressDialogUtils()
            }
            return instance as ProgressDialogUtils
        }
    }

    fun create(context: Context): ProgressDialogUtils {
        progressDialog = ProgressDialog(context)
        return this
    }

    fun withTitle(title: String): ProgressDialogUtils {
        if (progressDialog != null) {
            progressDialog?.setTitle(title)
        }
        return this
    }

    fun withMessage(message: String): ProgressDialogUtils {
        if (progressDialog != null) {
            progressDialog?.setMessage(message)
        }
        return this
    }

    fun cancelable(isCancelable: Boolean): ProgressDialogUtils {
        if (progressDialog != null) {
            progressDialog?.setCancelable(isCancelable)
        }
        return this
    }

    fun show() {
        if (progressDialog != null && !progressDialog?.isShowing!!) {
            progressDialog?.show()
        }
    }

    fun hide() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.dismiss()
        }
    }
}