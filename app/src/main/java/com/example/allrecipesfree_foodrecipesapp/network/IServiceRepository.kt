package com.example.allrecipesfree_foodrecipesapp.network

import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse

interface IServiceRepository {

    suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>

    suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>

    suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>>

    suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse>

    suspend fun getSearchPostsMenu(s: String): UseCaseResult<List<ServiceResponse>>

    suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>>
}