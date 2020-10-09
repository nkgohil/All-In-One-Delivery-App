package com.allinonedeliveryapp.pojo

data class ProfileUpdate(
    val created_at: String,
    val email: String,
    val profile_id: Int,
    val profile_image: String,
    val updated_at: String,
    val user_id: String,
    val username: String
)