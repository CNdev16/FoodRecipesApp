package com.example.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BetterFeaturedImage(

	@SerializedName("source_url")
	val sourceUrl: String? = null
) : Parcelable