package com.allinonedeliveryapp.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Category : ArrayList<CategoryItem>()

data class CategoryItem(
    val category_id: Int,
    val subcategory: List<Subcategory>,
    val title: String
)

@Parcelize
data class Subcategory(
    val color_hex: Int,
    val contact_number: Long,
    val description: String,
    val how_it_works: String,
    val image: String,
    val subcategory_id: Int,
    val title: String
) : Parcelable