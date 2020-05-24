package com.example.core.local

import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.RecipePosts
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.handleThrowable

class LocalDataSource(private var appDataBaseDao: AppDataBaseDao) : DataSource {
    override suspend fun getAllDataFromService(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCountryCategoryFromService(): UseCaseResult<List<CountryCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMenuCategoryByCountryCategoryIdFromService(countryCateId: Int): UseCaseResult<List<MenuCategory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipePostsByMenuCategoryIdFromService(menuCateId: Int): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllRecipePostsFromService(): UseCaseResult<List<RecipePosts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipePostsDetailsFromService(recipeId: Int): UseCaseResult<RecipePosts> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertCountryCategoryToLocal(countryCategory: CountryCategory): UseCaseResult<Long> {
        return try {
            val result = appDataBaseDao.insertCountryCategoryToLocal(countryCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getCountryCategoryFromLocal(): UseCaseResult<List<CountryCategory>> {
        return try {
            val result = appDataBaseDao.getCountryCategoryFromLocal()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun deleteCountryCategoryFromLocal(countryCategory: CountryCategory): UseCaseResult<Int> {
        return try {
            val result = appDataBaseDao.deleteCountryCategoryFromLocal(countryCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun insertMenuCategoryToLocal(menuCategory: MenuCategory): UseCaseResult<Long> {
        return try {
            val result = appDataBaseDao.insertMenuCategoryToLocal(menuCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getMenuCategoryByCountryCategoryIdFromLocal(countryCateId: Int): UseCaseResult<List<MenuCategory>> {
        return try {
            val result = appDataBaseDao.getMenuCategoryByIdFromLocal(countryCateId)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun deleteMenuCategoryFromLocal(menuCategory: MenuCategory): UseCaseResult<Int> {
        return try {
            val result = appDataBaseDao.deleteMenuCategoryFromLocal(menuCategory)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun insertRecipePostsToLocal(recipePosts: RecipePosts): UseCaseResult<Long> {
        return try {
            val result = appDataBaseDao.insertRecipePostsToLocal(recipePosts)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getAllRecipePostsFromLocal(): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = appDataBaseDao.getAllRecipePostsFromLocal()
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun getRecipePostsByMenuCategoryIdFromLocal(menuCateId: Int): UseCaseResult<List<RecipePosts>> {
        return try {
            val result = appDataBaseDao.getRecipePostsByIdFromLocal(menuCateId)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }    }

    override suspend fun getRecipePostsDetailsFromLocal(recipeId: Int): UseCaseResult<RecipePosts> {
        return try {
            val result = appDataBaseDao.getRecipePostsDetailsFromLocal(recipeId)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }

    override suspend fun deleteRecipePostsFromLocal(recipePosts: RecipePosts): UseCaseResult<Int> {
        return try {
            val result = appDataBaseDao.deleteRecipePostsFromLocal(recipePosts)
            UseCaseResult.Success(responseMessage = "Response Success.", data = result)
        } catch (t: Throwable) {
            t.handleThrowable()
        }
    }
}