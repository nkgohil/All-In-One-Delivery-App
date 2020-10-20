package com.allinonedeliveryapp.webapi

import android.text.TextUtils
import com.allinonedeliveryapp.pojo.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File


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

    private fun prepareImageBody(key: String, uri: String): MultipartBody.Part? {
        var body: MultipartBody.Part? = null
        if (!TextUtils.isEmpty(uri) && !uri.startsWith("http")) {
            val file = File(uri)
            val reqFile = RequestBody.create("image/*".toMediaType(), file)
            body = MultipartBody.Part.createFormData(key, file.name, reqFile)
        }
        return body
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

    fun profileUpdate(profile_image: String, profile_id: Int): Call<ProfileUpdate> {
        val imagePart: MultipartBody.Part? =
            prepareImageBody(
                "image",
                profile_image
            )
        return mApiService.profileUpdate(profile_id, profile_image)
    }


}
