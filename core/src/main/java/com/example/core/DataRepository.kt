package com.example.core

import com.example.core.data.ServiceResponse

class DataRepository (var remoteDataSource: DataSource) {

    suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = remoteDataSource.getCountryCategories(parentNo)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = remoteDataSource.getMenuCategories(parentNo)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = remoteDataSource.getPostsMenu(categoriesNo)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse> {
        return try {
            val result = remoteDataSource.getPostsMenuDetail(postsNo)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getSearchPostsMenu(): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = remoteDataSource.getSearchPostsMenu()
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

   suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = remoteDataSource.getAllPostsMenu()
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}