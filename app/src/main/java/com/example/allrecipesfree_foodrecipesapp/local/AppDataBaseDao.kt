package com.example.allrecipesfree_foodrecipesapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.allrecipesfree_foodrecipesapp.data.Favorite

@Dao
interface AppDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMenu(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavoriteMenu(): List<Favorite>
}