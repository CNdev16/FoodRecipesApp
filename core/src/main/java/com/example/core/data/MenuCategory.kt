package com.example.core.data

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "menu_category",
    indices = [Index(value = ["menu_cate_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = CountryCategory::class,
        parentColumns = ["country_id"],
        childColumns = ["menu_id"],
        onDelete = CASCADE
    )]
)
data class MenuCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "menu_id")
    var menuId: Int,
    @ColumnInfo(name = "country_cate_id")
    @SerializedName("country_cate_id")
    var countryCateId: Int,
    @ColumnInfo(name = "country_cate_name")
    @SerializedName("country_cate_name")
    var countryCateName: String,
    @ColumnInfo(name = "menu_cate_id")
    @SerializedName("menu_cate_id")
    var menuCateId: Int,
    @ColumnInfo(name = "menu_cate_name")
    @SerializedName("menu_cate_name")
    var menuCateName: String,
    @ColumnInfo(name = "menu_cate_img")
    @SerializedName("menu_cate_img")
    var menuCateImg: String,
    @ColumnInfo(name = "recipe_posts_list")
    @SerializedName("recipe_posts_list")
    var recipePostsList: List<RecipePosts>?
)