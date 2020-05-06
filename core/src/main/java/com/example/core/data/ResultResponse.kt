package com.example.core.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "result_response")
data class ResultResponse(
    @PrimaryKey
    @ColumnInfo(name = "parent_cate_id")
    @SerializedName("parent_cate_id")
    var parentCateId: Int,
    @ColumnInfo(name = "parent_cate_img")
    @SerializedName("parent_cate_img")
    var parentCateImg: String,
    @ColumnInfo(name = "parent_cate_name")
    @SerializedName("parent_cate_name")
    var parentCateName: String,
    @ColumnInfo(name = "sub_cate_list")
    @SerializedName("sub_cate_list")
    var subCateList: List<SubCate>?
)