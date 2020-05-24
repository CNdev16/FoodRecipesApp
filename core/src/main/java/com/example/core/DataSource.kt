package com.example.core

import com.example.core.data.*

interface DataSource {

    /*
    *   service.
     */
    suspend fun getAllDataFromService(): UseCaseResult<List<CountryCategory>>

    suspend fun getCountryCategoryFromService(): UseCaseResult<List<CountryCategory>>
    suspend fun getMenuCategoryByCountryCategoryIdFromService(countryCateId: Int): UseCaseResult<List<MenuCategory>>
    suspend fun getRecipePostsByMenuCategoryIdFromService(menuCateId: Int): UseCaseResult<List<RecipePosts>>
    suspend fun getAllRecipePostsFromService(): UseCaseResult<List<RecipePosts>>
    suspend fun getRecipePostsDetailsFromService(recipeId: Int): UseCaseResult<RecipePosts>

    /*
    *   local.
     */
    suspend fun insertCountryCategoryToLocal(countryCategory: CountryCategory): UseCaseResult<Long>

    suspend fun getCountryCategoryFromLocal(): UseCaseResult<List<CountryCategory>>
    suspend fun deleteCountryCategoryFromLocal(countryCategory: CountryCategory): UseCaseResult<Int>

    suspend fun insertMenuCategoryToLocal(menuCategory: MenuCategory): UseCaseResult<Long>
    suspend fun getMenuCategoryByCountryCategoryIdFromLocal(countryCateId: Int): UseCaseResult<List<MenuCategory>>
    suspend fun deleteMenuCategoryFromLocal(menuCategory: MenuCategory): UseCaseResult<Int>

    suspend fun insertRecipePostsToLocal(recipePosts: RecipePosts): UseCaseResult<Long>
    suspend fun getAllRecipePostsFromLocal(): UseCaseResult<List<RecipePosts>>
    suspend fun getRecipePostsByMenuCategoryIdFromLocal(menuCateId: Int): UseCaseResult<List<RecipePosts>>
    suspend fun getRecipePostsDetailsFromLocal(recipeId: Int): UseCaseResult<RecipePosts>
    suspend fun deleteRecipePostsFromLocal(recipePosts: RecipePosts): UseCaseResult<Int>
}