package com.example.core.remote

import com.example.core.data.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceEndPointInterface {

    @GET("data")
    fun getAllDataAsync():Deferred<List<CountryCategory>>
    @GET("parentCategory")
    fun getCountryCategoriesOnlyAsync(): Deferred<List<CountryCategory>>
    @GET("subCategory/{id}")
    fun getSubCategoriesOnlyAsync(@Path("id") parent_id: Int): Deferred<List<MenuCategory>>
    @GET("postsList/{id}")
    fun getPostsMenuOnlyAsync(@Path("id") cate_id: Int): Deferred<List<RecipePosts>>
    @GET("postsList")
    fun getAllPostsMenuOnlyAsync(): Deferred<List<RecipePosts>>
}