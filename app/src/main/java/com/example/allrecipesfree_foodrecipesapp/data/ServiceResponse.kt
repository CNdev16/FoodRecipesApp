package com.example.allrecipesfree_foodrecipesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceResponse(

    @SerializedName("date")
    val date: String? = null,

    @SerializedName("link")
    val link: String? = null,

    @SerializedName("better_featured_image")
    val betterFeaturedImage: BetterFeaturedImage? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: Title? = null,

    @SerializedName("content")
    val content: Content? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("image_category")
    val imageCategory: ImageCategory? = null,

    @SerializedName("favorite_status")
    var favoriteStatus: Boolean = false
) : Parcelable