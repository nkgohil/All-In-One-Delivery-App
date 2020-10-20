package com.allinonedeliveryapp.webapi

import com.allinonedeliveryapp.pojo.*
import retrofit2.Call
import retrofit2.http.*


interface WebAPIService {
    @FormUrlEncoded
    @POST("account/register/")
    fun createUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Register>

    @FormUrlEncoded
    @POST("account/login/")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Login>


    @GET("profile/{profile_id}/")
    fun userProfile(@Path("profile_id") profile_id: Int): Call<ProfileRetrieve>

    @GET("category")
    fun category(): Call<ArrayList<CategoryItem>>

    @FormUrlEncoded
    @PUT("update/{profile_id}")
    fun profileUpdate(
        @Path("profile_id") profile_id: Int,
        @Field("profile_image") profile_image: String
    ): Call<ProfileUpdate>

    @GET("category/photos")
    fun photos(): Call<ArrayList<PhotosItem>>


}   