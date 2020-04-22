package com.example.core.data

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("parent_cate_id")
    val parentCateId: Int,
    @SerializedName("parent_cate_img")
    val parentCateImg: String,
    @SerializedName("parent_cate_name")
    val parentCateName: String,
    @SerializedName("sub_cate_list")
    val subCateList: List<SubCate>? = null
)