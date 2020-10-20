package com.allinonedeliveryapp.pojo

class Photos : ArrayList<PhotosItem>()

data class PhotosItem(
    val id: Int,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val image5: String,
    val subcatagory: String
)