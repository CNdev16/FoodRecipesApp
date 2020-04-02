package com.example.core

import com.example.core.data.ServiceResponse

class RemoteRepositoryImpl (private val mService: ServiceEndPointInterface): RemoteRepository {
    override suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = mService.getCountryCategoriesAsync(parentNo).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = mService.getMenuCategoriesAsync(parentNo).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = mService.getPostsMenuAsync(categoriesNo).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse> {
        return try {
            val result = mService.getPostsMenuDetailAsync(postsNo).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getSearchPostsMenu(): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = mService.getSearchPostsMenuAsync().await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = mService.getAllPostsMenuAsync().await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }
}