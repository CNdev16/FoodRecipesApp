package com.example.core.remote

import android.util.Log
import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts
import com.example.core.handleErrorMessage
import com.example.core.handleThrowable
import com.google.gson.Gson

class RemoteDataSource(private val mApi: ApiEndPointInterface) : DataSource {
    override suspend fun getAllDataFromService(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = mApi.apiGetAllData()
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getCountryCategoryFromService(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = mApi.apiGetCountryCategory()
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getMenuCategoryByCountryCategoryIdFromService(cateId: Int): UseCaseResult<List<MenuCategory>> {
        return try {
            val result = mApi.apiGetMenuCategoryById(cateId)
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getRecipePostsByMenuCategoryIdFromService(menuId: Int): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = mApi.apiGetRecipePostsById(menuId)
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getAllRecipePostsFromService(): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = mApi.apiGetAllRecipesPosts()
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getRecipePostsDetailsFromService(recipeId: Int): UseCaseResult<RecipePosts> {
        return try {
            val result = mApi.apiGetRecipesPostsDetails(recipeId)
            if (!result.isSuccessful) {
                UseCaseResult.Error(
                    responseMessage = arrayListOf(
                        "${result.code()}",
                        result.errorBody()!!.handleErrorMessage()
                    )
                )
            } else {
                Log.d("xxxx", Gson().toJson(result.body()!!))
                UseCaseResult.Success(responseMessage = "Response Success.", data = result.body()!!)
            }
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun insertCountryCategoryToLocal(countryCategory: CountryCategory): UseCaseResult<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCountryCategoryFromLocal(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteCountryCategoryFromLocal(countryCategory: CountryCategory): UseCaseResult<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertMenuCategoryToLocal(menuCategory: MenuCategory): UseCaseResult<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMenuCategoryByCountryCategoryIdFromLocal(countryCateId: Int): UseCaseResult<List<MenuCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteMenuCategoryFromLocal(menuCategory: MenuCategory): UseCaseResult<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertRecipePostsToLocal(recipePosts: RecipePosts): UseCaseResult<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllRecipePostsFromLocal(): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipePostsByMenuCategoryIdFromLocal(menuCateId: Int): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipePostsDetailsFromLocal(recipeId: Int): UseCaseResult<RecipePosts> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteRecipePostsFromLocal(recipePosts: RecipePosts): UseCaseResult<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}