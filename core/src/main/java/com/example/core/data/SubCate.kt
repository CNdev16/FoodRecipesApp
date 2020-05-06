package com.example.core.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "sub_cate")
data class SubCate(
    @PrimaryKey
    @ColumnInfo(name = "parent_id")
    @SerializedName("parent_id")
    var parentId: Int,
    @ColumnInfo(name = "parent_name")
    @SerializedName("parent_name")
    var parentName: String,
    @ColumnInfo(name = "posts_list")
    @SerializedName("posts_list")
    var postsList: List<Posts>?,
    @ColumnInfo(name = "sub_cate_id")
    @SerializedName("sub_cate_id")
    var subCateId: Int,
    @ColumnInfo(name = "sub_cate_img")
    @SerializedName("sub_cate_img")
    var subCateImg: String,
    @ColumnInfo(name = "sub_cate_name")
    @SerializedName("sub_cate_name")
    var subCateName: String
)