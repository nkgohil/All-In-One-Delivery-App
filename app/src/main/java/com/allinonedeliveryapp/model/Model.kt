package com.allinonedeliveryapp.model

import com.allinonedeliveryapp.R

enum class Model private constructor(val layoutResId: Int) {
    RED(R.layout.onboardone),
    BLUE(R.layout.onboardtwo),
    GREEN(R.layout.onboardthree)
}