package com.allinonedeliveryapp.util

import android.view.View
import com.allinonedeliveryapp.pojo.Subcategory

interface OnRecyclerItemClickListener<T> {
    fun onItemClick(view: View?, position: Int, obj: Subcategory)
}