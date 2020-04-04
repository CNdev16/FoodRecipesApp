package com.example.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.Favorite

@Dao
interface AppDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMenu(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavoriteMenu(): List<Favorite>
}