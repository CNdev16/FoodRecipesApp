package com.example.core

import com.example.core.data.*
import retrofit2.HttpException

class DataRepository(
    private var remoteDataSource: DataSource,
    private var localDataSource: DataSource
) {

    suspend fun getAllData(isNetworkEnabled: Boolean): UseCaseResult<List<CountryCategory>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getAllData()
            }
            localDataSource.getCountryCategoryDataFromDb()

        } catch (t: Throwable) {
            when (t) {
                is HttpException -> {
                    UseCaseResult.Error(responseMessage = "Code : ${t.code()}\nMessage: ${t.message()}")
                }
                else -> {
                    UseCaseResult.Error(responseMessage = t.message ?: "")
                }
            }
        }
    }

    suspend fun getCountryCategoriesOnly(isNetworkEnabled: Boolean): UseCaseResult<List<CountryCategory>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getCountryCategoriesOnly()
            }
            localDataSource.getCountryCategoryDataFromDb()
        } catch (t: Throwable) {
            when (t) {
                is HttpException -> {
                    UseCaseResult.Error(responseMessage = "Code : ${t.code()}\nMessage: ${t.message()}")
                }
                else -> {
                    UseCaseResult.Error(responseMessage = t.message ?: "")
                }
            }
        }
    }

    suspend fun getSubCategoriesOnly(parent_id: Int, isNetworkEnabled: Boolean): UseCaseResult<List<MenuCategory>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getSubCategoriesOnly(parent_id)
            }
            remoteDataSource.getSubCategoriesOnly(parent_id)
            //localDataSource.getAllPostsDataFromDb()
        } catch (t: Throwable) {
            when (t) {
                is HttpException -> {
                    UseCaseResult.Error(responseMessage = "Code : ${t.code()}\nMessage: ${t.message()}")
                }
                else -> {
                    UseCaseResult.Error(responseMessage = t.message ?: "")
                }
            }
        }
    }

    suspend fun getPostsMenuOnly(cate_id: Int, isNetworkEnabled: Boolean): UseCaseResult<List<RecipePosts>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getPostsMenuOnly(cate_id)
            }
            remoteDataSource.getPostsMenuOnly(cate_id)
            //localDataSource.getAllPostsDataFromDb()
        } catch (t: Throwable) {
            when (t) {
                is HttpException -> {
                    UseCaseResult.Error(responseMessage = "Code : ${t.code()}\nMessage: ${t.message()}")
                }
                else -> {
                    UseCaseResult.Error(responseMessage = t.message ?: "")
                }
            }
        }
    }

    suspend fun getAllPostsMenuOnly(isNetworkEnabled: Boolean): UseCaseResult<List<RecipePosts>> {
        return try {
            if (isNetworkEnabled) {
                remoteDataSource.getAllPostsMenuOnly()
            }
            remoteDataSource.getAllPostsMenuOnly()
            //localDataSource.getAllPostsDataFromDb()
        } catch (t: Throwable) {
            when (t) {
                is HttpException -> {
                    UseCaseResult.Error(responseMessage = "Code : ${t.code()}\nMessage: ${t.message()}")
                }
                else -> {
                    UseCaseResult.Error(responseMessage = t.message ?: "")
                }
            }
        }
    }

    suspend fun addCountryCategoryDataToDb(countryCategory: CountryCategory): UseCaseResult<Long> {
        return try {
            val result = localDataSource.addCountryCategoryDataToDb(countryCategory)
            result
        } catch (t: Throwable) {
            UseCaseResult.Error(responseMessage = t.message ?: "")
        }
    }

    suspend fun getCountryCategoryDataFromDb(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = localDataSource.getCountryCategoryDataFromDb()
            result
        } catch (t: Throwable) {
            UseCaseResult.Error(responseMessage = t.message ?: "")
        }
    }

    suspend fun deleteCountryCategoryFromDb(countryCategory: CountryCategory): UseCaseResult<Int> {
        return try {
            val result = localDataSource.deleteCountryCategoryFromDb(countryCategory)
            result
        } catch (t: Throwable) {
            UseCaseResult.Error(responseMessage = t.message ?: "")
        }
    }
}