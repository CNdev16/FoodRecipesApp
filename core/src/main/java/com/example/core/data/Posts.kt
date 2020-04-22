package com.example.core.data

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("post_author")
    val postAuthor: String,
    @SerializedName("post_content")
    val postContent: String,
    @SerializedName("post_date")
    val postDate: String,
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("post_img")
    val postImg: String,
    @SerializedName("post_name")
    val postName: String,
    @SerializedName("post_status")
    val postStatus: String,
    @SerializedName("post_title")
    val postTitle: String,
    @SerializedName("post_type")
    val postType: String
)