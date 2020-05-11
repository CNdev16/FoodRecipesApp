package com.example.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.Favorite
import com.example.core.data.ResultResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMenu(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavoriteMenu(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPostsData(allPosts: ResultResponse): Long

    @Query("SELECT * FROM result_response")
    suspend fun getAllPostsData(): List<ResultResponse>
}