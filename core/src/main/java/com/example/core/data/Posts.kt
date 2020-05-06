package com.example.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Posts(
    @ColumnInfo(name = "post_author")
    @SerializedName("post_author")
    var postAuthor: String,
    @ColumnInfo(name = "post_content")
    @SerializedName("post_content")
    var postContent: String,
    @ColumnInfo(name = "post_date")
    @SerializedName("post_date")
    var postDate: String,
    @PrimaryKey
    @ColumnInfo(name = "post_id")
    @SerializedName("post_id")
    var postId: Int,
    @ColumnInfo(name = "post_img")
    @SerializedName("post_img")
    var postImg: String,
    @ColumnInfo(name = "post_name")
    @SerializedName("post_name")
    var postName: String,
    @ColumnInfo(name = "post_status")
    @SerializedName("post_status")
    var postStatus: String,
    @ColumnInfo(name = "post_title")
    @SerializedName("post_title")
    var postTitle: String,
    @ColumnInfo(name = "post_type")
    @SerializedName("post_type")
    var postType: String
)