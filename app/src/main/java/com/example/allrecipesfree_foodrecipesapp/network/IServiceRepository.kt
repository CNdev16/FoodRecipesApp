package com.example.allrecipesfree_foodrecipesapp.network

import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse

interface IServiceRepository {

    //get category country param = [0].
    suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>

    //get category menu param = [parentNo].
    suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>

    //get posts menu = [categoriesNo].
    suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>>

    //get posts menu detail = [postsNo].
    suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse>

    //get search posts.
    suspend fun getSearchPostsMenu(): UseCaseResult<List<ServiceResponse>>

    //get all posts.
    suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>>
}