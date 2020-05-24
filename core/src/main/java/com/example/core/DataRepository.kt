package com.example.core

import com.example.core.data.*

class DataRepository(
    private var remoteDataSource: DataSource,
    private var localDataSource: DataSource
) {

    suspend fun getAllData(isNetworkEnabled: Boolean): UseCaseResult<List<CountryCategory>> {
        return try {
            remoteDataSource.getAllDataFromService()
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun getCountryCategory(isNetworkEnabled: Boolean): UseCaseResult<List<CountryCategory>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getCountryCategoryFromService()
            } else {
                localDataSource.getCountryCategoryFromLocal()
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun getMenuCategoryByCountryCategoryId(
        countryId: Int,
        isNetworkEnabled: Boolean
    ): UseCaseResult<List<MenuCategory>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getMenuCategoryByCountryCategoryIdFromService(countryId)
            } else {
                localDataSource.getMenuCategoryByCountryCategoryIdFromLocal(countryId)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun getRecipePostsByMenuCategoryId(
        menuCateId: Int,
        isNetworkEnabled: Boolean
    ): UseCaseResult<List<RecipePosts>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getRecipePostsByMenuCategoryIdFromService(menuCateId)
            } else {
                localDataSource.getRecipePostsByMenuCategoryIdFromLocal(menuCateId)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun getAllRecipePosts(isNetworkEnabled: Boolean): UseCaseResult<List<RecipePosts>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getAllRecipePostsFromService()
            } else {
                localDataSource.getAllRecipePostsFromLocal()
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun getRecipePostsDetails(
        recipeId: Int,
        isNetworkEnabled: Boolean
    ): UseCaseResult<RecipePosts> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getRecipePostsDetailsFromService(recipeId)
            } else {
                localDataSource.getRecipePostsDetailsFromLocal(recipeId)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun insertCountryCategoryToLocal(countryCategory: CountryCategory): UseCaseResult<Long> {
        return try {
            val result = localDataSource.insertCountryCategoryToLocal(countryCategory)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun deleteCountryCategoryFromLocal(countryCategory: CountryCategory): UseCaseResult<Int> {
        return try {
            val result = localDataSource.deleteCountryCategoryFromLocal(countryCategory)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun insertMenuCategoryToLocal(menuCategory: MenuCategory): UseCaseResult<Long> {
        return try {
            val result = localDataSource.insertMenuCategoryToLocal(menuCategory)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun deleteMenuCategoryFromLocal(menuCategory: MenuCategory): UseCaseResult<Int> {
        return try {
            val result = localDataSource.deleteMenuCategoryFromLocal(menuCategory)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun insertRecipePostsToLocal(recipePosts: RecipePosts): UseCaseResult<Long> {
        return try {
            val result = localDataSource.insertRecipePostsToLocal(recipePosts)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    suspend fun deleteRecipePostsFromLocal(recipePosts: RecipePosts): UseCaseResult<Int> {
        return try {
            val result = localDataSource.deleteRecipePostsFromLocal(recipePosts)
            result
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

}