package com.example.core.local

import androidx.room.*
import com.example.core.data.Favorite
import com.example.core.data.CountryCategory

@Dao
interface AppDataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMenu(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavoriteMenu(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCountryCategoryData(countryCategory: CountryCategory): Long

    @Query("SELECT * FROM country_category")
    fun getCountryCategoryData(): List<CountryCategory>

    @Delete
    fun deleteCountryCategoryFromDb(countryCategory: CountryCategory):Int
}