package com.example.core.data

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "sub_cate",
    foreignKeys = [ForeignKey(
        entity = ResultResponse::class,
        parentColumns = ["parent_cate_id"],
        childColumns = ["sub_cate_id"],
        onDelete = CASCADE
    )]
)
data class SubCate(
    @ColumnInfo(name = "parent_id")
    @SerializedName("parent_id")
    var parentId: Int,
    @ColumnInfo(name = "parent_name")
    @SerializedName("parent_name")
    var parentName: String,
    @ColumnInfo(name = "posts_list")
    @SerializedName("posts_list")
    var postsList: List<Posts>?,
    @PrimaryKey
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