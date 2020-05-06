package com.example.core.local

import com.example.core.DataSource
import com.example.core.UseCaseResult
import com.example.core.data.Posts
import com.example.core.data.ResultResponse
import com.example.core.data.SubCate
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private var appDataBaseDao: AppDataBaseDao) : DataSource {
    override suspend fun getAllData(): UseCaseResult<List<ResultResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCountryCategoriesOnly(): UseCaseResult<List<ResultResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getSubCategoriesOnly(parent_id: Int): UseCaseResult<List<SubCate>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPostsMenuOnly(cate_id: Int): UseCaseResult<List<Posts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllPostsMenuOnly(): UseCaseResult<List<Posts>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addAllPostsDataToDb(resultResponse: ResultResponse): UseCaseResult<Long>{
        return try {
            val result = appDataBaseDao.addAllPostsData(resultResponse)
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun getAllPostsDataFromDb(): UseCaseResult<List<ResultResponse>> {
        return try {
            val result = appDataBaseDao.getAllPostsData()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }


}