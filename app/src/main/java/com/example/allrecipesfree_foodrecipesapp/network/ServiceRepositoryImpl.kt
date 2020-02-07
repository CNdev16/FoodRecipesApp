package com.example.allrecipesfree_foodrecipesapp.network

import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import java.lang.Exception


class ServiceRepositoryImpl(private val postsService: PostsService) : IServiceRepository {
    override suspend fun getCountryCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = postsService.getCountryCategoriesAsync(parentNo).await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getMenuCategories(parentNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = postsService.getMenuCategoriesAsync(parentNo).await()
            UseCaseResult.Success(result)
        } catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getPostsMenu(categoriesNo: Int): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = postsService.getPostsMenuAsync(categoriesNo).await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getPostsMenuDetail(postsNo: Int): UseCaseResult<ServiceResponse> {
        return try {
            val result = postsService.getPostsMenuDetailAsync(postsNo).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getSearchPostsMenu(s: String): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = postsService.getSearchPostsMenuAsync(s).await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getAllPostsMenu(): UseCaseResult<List<ServiceResponse>> {
        return try {
            val result = postsService.getAllPostsMenuAsync().await()
            UseCaseResult.Success(result)
        }catch (e: Exception){
            UseCaseResult.Error(e)
        }    }

}