package com.example.allrecipesfree_foodrecipesapp.network

import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//set endpoint.
interface PostsService{
    //get country category endpoint.
    @GET("wp-json/wp/v2/categories?&fields=id,description,name,image_category")
    fun getCountryCategoriesAsync(@Query("parent") parentNo: Int): Deferred<List<ServiceResponse>>

    //get menu category endpoint.
    @GET("wp-json/wp/v2/categories")
    fun getMenuCategoriesAsync(@Query("parent") parentNo: Int): Deferred<List<ServiceResponse>>

    //get posts menu endpoint.
    @GET("wp-json/wp/v2/posts")
    fun getPostsMenuAsync(@Query("categories") categoriesNo: Int): Deferred<List<ServiceResponse>>

    //get posts menu detail endpoint.
    @GET("wp-json/wp/v2/posts/{id}")
    fun getPostsMenuDetailAsync(@Path("id") postsId: Int): Deferred<ServiceResponse>

    //get search posts endpoint.
    //get all posts and filter after get result success.
    @GET("wp-json/wp/v2/posts")
    fun getSearchPostsMenuAsync() : Deferred<List<ServiceResponse>>

    //get all posts endpoint.
    @GET("wp-json/wp/v2/posts")
    fun getAllPostsMenuAsync() : Deferred<List<ServiceResponse>>
}