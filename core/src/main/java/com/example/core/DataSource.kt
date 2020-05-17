package com.example.core

import com.example.core.data.*

interface DataSource{

    suspend fun getAllData(): UseCaseResult<List<CountryCategory>>
    suspend fun getCountryCategoriesOnly(): UseCaseResult<List<CountryCategory>>
    suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<MenuCategory>>
    suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<RecipePosts>>
    suspend fun getAllPostsMenuOnly(): UseCaseResult<List<RecipePosts>>
    suspend fun addCountryCategoryDataToDb(countryCategory: CountryCategory):UseCaseResult<Long>
    suspend fun getCountryCategoryDataFromDb():UseCaseResult<List<CountryCategory>>
    suspend fun deleteCountryCategoryFromDb(countryCategory: CountryCategory):UseCaseResult<Int>
}