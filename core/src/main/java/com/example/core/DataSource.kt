package com.example.core

import com.example.core.data.*

interface DataSource{

    suspend fun getAllData(): UseCaseResult<List<ResultResponse>>
    suspend fun getCountryCategoriesOnly(): UseCaseResult<List<ResultResponse>>
    suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<SubCate>>
    suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<Posts>>
    suspend fun getAllPostsMenuOnly(): UseCaseResult<List<Posts>>
    suspend fun addAllPostsDataToDb(resultResponse: ResultResponse):UseCaseResult<Long>
    suspend fun getAllPostsDataFromDb():UseCaseResult<List<ResultResponse>>
}