package com.example.core.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "country_category",
    indices = [Index(value = ["country_cate_id"], unique = true)]
)
data class CountryCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    var countryId: Int,
    @ColumnInfo(name = "country_cate_id")
    @SerializedName("country_cate_id")
    var countryCateId: Int,
    @ColumnInfo(name = "country_cate_name")
    @SerializedName("country_cate_name")
    var countryCateName: String,
    @ColumnInfo(name = "country_cate_img")
    @SerializedName("country_cate_img")
    var countryCateImg: String,
    @ColumnInfo(name = "menu_category_list")
    @SerializedName("menu_category_list")
    var menuCategoryList: List<MenuCategory>?
) : Parcelable