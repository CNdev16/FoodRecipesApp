package com.example.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(

	@SerializedName("rendered")
	val rendered: String? = null,

	@SerializedName("protected")
	val jsonMemberProtected: Boolean? = null
) : Parcelable