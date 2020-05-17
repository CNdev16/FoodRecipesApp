package com.example.core.local

import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory

class LocalDataSource(private var appDataBaseDao: AppDataBaseDao) : DataSource {
    override suspend fun getAllData(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCountryCategoriesOnly(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<MenuCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllPostsMenuOnly(): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addCountryCategoryDataToDb(countryCategory: CountryCategory): UseCaseResult<Long> {
        return try {
            val result = appDataBaseDao.addCountryCategoryData(countryCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (e: Exception) {
            UseCaseResult.Error(responseMessage = e.message ?: "")
        }
    }

    override suspend fun getCountryCategoryDataFromDb(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = appDataBaseDao.getCountryCategoryData()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            UseCaseResult.Error(responseMessage = t.message ?: "")
        }
    }

    override suspend fun deleteCountryCategoryFromDb(countryCategory: CountryCategory): UseCaseResult<Int> {
        return try {
            val result = appDataBaseDao.deleteCountryCategoryFromDb(countryCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            UseCaseResult.Error(responseMessage = t.message ?: "")
        }
    }
}