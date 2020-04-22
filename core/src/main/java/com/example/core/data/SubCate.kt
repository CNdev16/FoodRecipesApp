package com.example.core.data

import com.google.gson.annotations.SerializedName

data class SubCate(
    @SerializedName("parent_id")
    val parentId: Int,
    @SerializedName("parent_name")
    val parentName: String,
    @SerializedName("posts_list")
    val postsList: List<Posts>,
    @SerializedName("sub_cate_id")
    val subCateId: Int,
    @SerializedName("sub_cate_img")
    val subCateImg: String,
    @SerializedName("sub_cate_name")
    val subCateName: String
)