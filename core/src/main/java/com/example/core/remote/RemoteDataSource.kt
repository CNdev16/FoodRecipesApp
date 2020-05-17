package com.example.core.remote

import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import retrofit2.HttpException

class RemoteDataSource(private val mService: ServiceEndPointInterface) : DataSource {
    override suspend fun getAllData(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = mService.getAllDataAsync().await()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
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

    override suspend fun getCountryCategoriesOnly(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = mService.getCountryCategoriesOnlyAsync().await()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
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

    override suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<MenuCategory>> {
        return try {
            val result = mService.getSubCategoriesOnlyAsync(parent_id).await()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
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

    override suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = mService.getPostsMenuOnlyAsync(cate_id).await()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
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

    override suspend fun getAllPostsMenuOnly(): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = mService.getAllPostsMenuOnlyAsync().await()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
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

    override suspend fun addCountryCategoryDataToDb(countryCategory: CountryCategory): UseCaseResult<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCountryCategoryDataFromDb(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteCountryCategoryFromDb(countryCategory: CountryCategory): UseCaseResult<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}