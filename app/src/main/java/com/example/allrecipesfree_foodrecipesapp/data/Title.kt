package com.example.allrecipesfree_foodrecipesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Title(
	@SerializedName("rendered")
	val rendered: String? = null
) : Parcelable