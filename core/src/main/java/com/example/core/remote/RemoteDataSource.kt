package com.example.core.remote

import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.*
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(private val mService: ServiceEndPointInterface) : DataSource {
    override suspend fun getAllData(): UseCaseResult<List<ResultResponse>> {
        return try {
            val result = mService.getAllDataAsync().await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getCountryCategoriesOnly(): UseCaseResult<List<ResultResponse>> {
        return try {
            val result = mService.getCountryCategoriesOnlyAsync().await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<SubCate>> {
        return try {
            val result = mService.getSubCategoriesOnlyAsync(parent_id).await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<Posts>> {
        return try {
            val result = mService.getPostsMenuOnlyAsync(cate_id).await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getAllPostsMenuOnly(): UseCaseResult<List<Posts>> {
        return try {
            val result = mService.getAllPostsMenuOnlyAsync().await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun addAllPostsDataToDb(resultResponse: ResultResponse): UseCaseResult<Long>{
        return try {
            UseCaseResult.Success(-1L)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getAllPostsDataFromDb(): UseCaseResult<List<ResultResponse>> {
        return try {
            UseCaseResult.Success(ArrayList())
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}