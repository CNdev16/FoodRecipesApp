package com.example.core.data

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "recipe_posts",
    indices = [Index(value = ["recipe_post_id"], unique = true)]
)
data class RecipePosts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    var recipeId: Int,
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
    @ColumnInfo(name = "recipe_post_id")
    @SerializedName("recipe_post_id")
    var recipePostId: Int,
    @ColumnInfo(name = "recipe_post_author")
    @SerializedName("recipe_post_author")
    var recipePostAuthor: String,
    @ColumnInfo(name = "recipe_post_name")
    @SerializedName("recipe_post_name")
    var recipePostName: String,
    @ColumnInfo(name = "recipe_post_date")
    @SerializedName("recipe_post_date")
    var recipePostDate: String,
    @ColumnInfo(name = "recipe_post_title")
    @SerializedName("recipe_post_title")
    var recipePostTitle: String,
    @ColumnInfo(name = "recipe_post_content")
    @SerializedName("recipe_post_content")
    var recipeContent: String,
    @ColumnInfo(name = "recipe_post_status")
    @SerializedName("recipe_post_status")
    var recipePostStatus: String,
    @ColumnInfo(name = "recipe_post_type")
    @SerializedName("recipe_post_type")
    var recipePostType: String,
    @ColumnInfo(name = "recipe_post_img")
    @SerializedName("recipe_post_img")
    var recipePostImg: String,
    @ColumnInfo(name = "recipe_post_modified")
    @SerializedName("recipe_post_modified")
    var recipePostModified: String,
    @ColumnInfo(name = "recipe_post_favorite")
    @SerializedName("recipe_post_favorite")
    var recipePostFavorite: Boolean
) : Parcelable