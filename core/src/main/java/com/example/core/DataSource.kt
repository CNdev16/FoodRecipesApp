package com.example.core

import com.example.core.data.ServiceResponse

interface DataSource{
    suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>
    suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>>
    suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>>
    suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse>
    suspend fun getSearchPostsMenu(): UseCaseResult<List<ServiceResponse>>
    suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>>
}