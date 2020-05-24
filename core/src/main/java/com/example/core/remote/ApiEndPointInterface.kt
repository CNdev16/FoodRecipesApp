package com.example.core.remote

import com.example.core.data.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPointInterface {

    @GET("allData")
    suspend fun apiGetAllData(): Response<List<CountryCategory>>

    @GET("countryCategory")
    suspend fun apiGetCountryCategory(): Response<List<CountryCategory>>

    @GET("menuCategoryById/{id}")
    suspend fun apiGetMenuCategoryById(@Path("id") parent_id: Int): Response<List<MenuCategory>>

    @GET("recipePostsById/{id}")
    suspend fun apiGetRecipePostsById(@Path("id") cate_id: Int): Response<List<RecipePosts>>

    @GET("recipePosts")
    suspend fun apiGetAllRecipesPosts(): Response<List<RecipePosts>>

    @GET("recipePosts/{id}")
    suspend fun apiGetRecipesPostsDetails(@Path("id") recipeId: Int): Response<RecipePosts>
}