package com.example.core.remote

import com.example.core.data.ServiceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceEndPointInterface {
    @GET("wp-json/wp/v2/categories?&fields=id,description,name,image_category")
    fun getCountryCategoriesAsync(@Query("parent") parentNo: Int): Deferred<List<ServiceResponse>>

    @GET("wp-json/wp/v2/categories")
    fun getMenuCategoriesAsync(@Query("parent") parentNo: Int): Deferred<List<ServiceResponse>>

    @GET("wp-json/wp/v2/posts")
    fun getPostsMenuAsync(@Query("categories") categoriesNo: Int): Deferred<List<ServiceResponse>>

    @GET("wp-json/wp/v2/posts/{id}")
    fun getPostsMenuDetailAsync(@Path("id") postsId: Int): Deferred<ServiceResponse>

    @GET("wp-json/wp/v2/posts")
    fun getSearchPostsMenuAsync() : Deferred<List<ServiceResponse>>

    @GET("wp-json/wp/v2/posts")
    fun getAllPostsMenuAsync() : Deferred<List<ServiceResponse>>
}