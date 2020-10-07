package com.allinonedeliveryapp.util

import android.content.Context
import android.content.SharedPreferences
import com.allinonedeliveryapp.AppClass


class PreferenceHelper private constructor() {
    private val USER_NAME = "USER_NAME"
    private val AUTH_TOKEN = "AUTH_TOKEN"
    private val FCM_TOKEN = "FCM_TOKEN"
    private val IS_LOGIN = "IS_LOGIN"
    private val IS_FCM_TOKEN_UPDATED = "IS_FCM_TOKEN_UPDATED"
    private val USER_MAIL = "USER_MAIL"
    private val DOMAIN_NAME = "DOMAIN_NAME"
    private val IS_INTRO_SHOWN = "IS_INTRO_SHOWN"
    private val CMS_INFO = "CMS_INFO"
    private val VERIFY_USER = "VERIFY_USER"
    private val DOMAIN_ID = "DOMAIN_ID"
    private val USER_ID = "USER_ID"
    private val DEPT_ID = "DEPT_ID"
    private val BACK = "BACK"
    private val EXTRA = "EXTRA"

    private val mPrefs: SharedPreferences

    init {
        val application = AppClass.getInstance()
        mPrefs = application.getSharedPreferences("all_in_one_delivery_pref", Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: PreferenceHelper? = null

        @JvmStatic
        fun getInstance(): PreferenceHelper {
            if (instance == null) {
                instance = PreferenceHelper()
            }
            return instance as PreferenceHelper
        }
    }

    var username: String?
        get() = mPrefs.getString(USER_NAME, "")
        set(userId) = mPrefs.edit().putString(USER_NAME, userId).apply()

    var userId: String?
        get() = mPrefs.getString(USER_ID, "")
        set(userId) = mPrefs.edit().putString(USER_ID, userId).apply()
    var authToken: String?
        get() = mPrefs.getString(AUTH_TOKEN, "")
        set(token) = mPrefs.edit().putString(AUTH_TOKEN, token).apply()

    var fcmToken: String?
        get() = mPrefs.getString(FCM_TOKEN, "")
        set(token) = mPrefs.edit().putString(FCM_TOKEN, token).apply()

    var fcmTokenUpdate: Boolean?
        get() = mPrefs.getBoolean(IS_FCM_TOKEN_UPDATED, false)
        set(isUpdated) = mPrefs.edit().putBoolean(IS_FCM_TOKEN_UPDATED, isUpdated!!).apply()

    var isIntroShown: Boolean?
        get() = mPrefs.getBoolean(IS_INTRO_SHOWN, false)
        set(isShown) = mPrefs.edit().putBoolean(IS_INTRO_SHOWN, isShown!!).apply()

    var isLogin: Boolean?
        get() = mPrefs.getBoolean(IS_LOGIN, false)
        set(isUserLogin) = mPrefs.edit().putBoolean(IS_LOGIN, isUserLogin!!).apply()

    var userMail: String?
        get() = mPrefs.getString(USER_MAIL, "")
        set(email) = mPrefs.edit().putString(USER_MAIL, email).apply()

    var domainName: String?
        get() = mPrefs.getString(DOMAIN_NAME, "")
        set(domainName) = mPrefs.edit().putString(DOMAIN_NAME, domainName).apply()

    var backPressedFlag: Boolean?
        get() = mPrefs.getBoolean(BACK, false)
        set(backPressedFlag) = mPrefs.edit().putBoolean(BACK, backPressedFlag!!).apply()

    var extra: Int?
        get() = mPrefs.getInt(EXTRA, 0)
        set(extra) = mPrefs.edit().putInt(EXTRA, extra!!).apply()


    fun clearPref() {
        mPrefs.edit().remove(AUTH_TOKEN).apply()
        mPrefs.edit().remove(IS_LOGIN).apply()
        mPrefs.edit().remove(FCM_TOKEN).apply()
        mPrefs.edit().remove(IS_FCM_TOKEN_UPDATED).apply()
        mPrefs.edit().remove(IS_INTRO_SHOWN).apply()
        mPrefs.edit().remove(USER_MAIL).apply()
        mPrefs.edit().remove(DOMAIN_NAME).apply()
        mPrefs.edit().remove(CMS_INFO).apply()
        mPrefs.edit().remove(VERIFY_USER).apply()
        mPrefs.edit().remove(DOMAIN_ID).apply()
        mPrefs.edit().remove(USER_ID).apply()
        mPrefs.edit().remove(DEPT_ID).apply()
        mPrefs.edit().remove(BACK).apply()
        mPrefs.edit().remove(EXTRA).apply()
    }

}
