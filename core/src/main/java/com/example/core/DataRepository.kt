package com.example.core

import com.example.core.data.*

class DataRepository (private var remoteDataSource: DataSource) {

    suspend fun getAllData(): UseCaseResult<List<ResultResponse>> {
        return try {
            val result = remoteDataSource.getAllData()
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getCountryCategoriesOnly(): UseCaseResult<List<ResultResponse>> {
        return try {
            val result = remoteDataSource.getCountryCategoriesOnly()
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<SubCate>> {
        return try {
            val result = remoteDataSource.getSubCategoriesOnly(parent_id)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<Posts>> {
        return try {
            val result = remoteDataSource.getPostsMenuOnly(cate_id)
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    suspend fun getAllPostsMenuOnly(): UseCaseResult<List<Posts>> {
        return try {
            val result = remoteDataSource.getAllPostsMenuOnly()
            result
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}