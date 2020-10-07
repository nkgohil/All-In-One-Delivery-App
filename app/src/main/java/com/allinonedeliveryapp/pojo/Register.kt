package com.allinonedeliveryapp.pojo

data class Register(
    val email: String,
    val profile_id: Int,
    val profile_image: String,
    val token: String,
    val username: String
)