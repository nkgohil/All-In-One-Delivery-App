package com.allinonedeliveryapp

enum class Model private constructor(val layoutResId: Int) {
    RED(R.layout.onboardone),
    BLUE(R.layout.onboardtwo),
    GREEN(R.layout.onboardthree)
}