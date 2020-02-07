package com.example.allrecipesfree_foodrecipesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageCategory(

    @SerializedName("guid")
    val guid: String? = null
):Parcelable