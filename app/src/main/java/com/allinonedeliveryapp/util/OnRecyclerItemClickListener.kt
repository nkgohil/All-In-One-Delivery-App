package com.allinonedeliveryapp.util

import android.view.View

interface OnRecyclerItemClickListener<T> {
    fun onItemClick(view: View?, position: Int, obj: T)
}