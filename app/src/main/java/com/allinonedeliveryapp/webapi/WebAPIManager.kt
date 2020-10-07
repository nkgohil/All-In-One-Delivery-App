package com.allinonedeliveryapp.webapi

import com.allinonedeliveryapp.pojo.CategoryItem
import com.allinonedeliveryapp.pojo.Login
import com.allinonedeliveryapp.pojo.ProfileRetrieve
import com.allinonedeliveryapp.pojo.Register
import retrofit2.Call


class WebAPIManager private constructor() {

    private val mApiService: WebAPIService = WebAPIServiceFactory.newInstance().makeServiceFactory()

    companion object {

        private var INSTANCE: WebAPIManager? = null

        val instance: WebAPIManager
            get() {
                if (INSTANCE == null) {
                    INSTANCE = WebAPIManager()
                }
                return INSTANCE as WebAPIManager
            }
    }

    fun getData(email: String, username: String, password: String): Call<Register> {
        return mApiService.createUser(email, username, password)
    }

    fun login(email: String, password: String): Call<Login> {
        return mApiService.userLogin(email, password)
    }

    fun userProfile(profile_id: Int): Call<ProfileRetrieve> {
        return mApiService.userProfile(profile_id)
    }

    fun category(): Call<ArrayList<CategoryItem>> {
        return mApiService.category()
    }

}
