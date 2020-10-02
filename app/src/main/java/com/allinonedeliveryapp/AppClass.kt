package com.allinonedeliveryapp

import android.app.Application

class AppClass : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion
    object {
        private var instance: AppClass? = null

        @JvmStatic
        fun getInstance(): AppClass {
            return instance as AppClass
        }
    }
}
