package com.example.core.local

import androidx.room.*
import com.example.core.data.Favorite
import com.example.core.data.CountryCategory
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts

@Dao
interface AppDataBaseDao {

    /*
    *   Insert favorite menu to local database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMenuToLocal(favorite: Favorite)

    /*
    *   Get favorite menu from local database.
    *   @return list favorite
     */
    @Query("SELECT * FROM favorite")
    fun getFavoriteMenuFromLocal(): List<Favorite>

    /*
    *   Insert country category to local database.
    *   @return long id
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountryCategoryToLocal(countryCategory: CountryCategory): Long

    /*
    *   Get country category from local database.
    *   @return list country category
     */
    @Query("SELECT * FROM country_category")
    fun getCountryCategoryFromLocal(): List<CountryCategory>

    /*
    *   Delete country category from local.
    *   @return int
     */
    @Delete
    fun deleteCountryCategoryFromLocal(countryCategory: CountryCategory): Int

    /*
    *   Insert menu category to local database.
    *   @return long id
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuCategoryToLocal(menuCategory: MenuCategory): Long

    /*
    *   Get all menu category from local database.
    *   @return list menu category
     */
    @Query("SELECT * FROM menu_category")
    fun getAllMenuCategoryFromLocal(): List<MenuCategory>

    /*
    *   Get menu category by category_id from local database.
    *   @param int 'cateId'
    *   @return list country category
     */
    @Query("SELECT * FROM menu_category WHERE menu_category.country_cate_id=:countryCateId")
    fun getMenuCategoryByIdFromLocal(countryCateId: Int): List<MenuCategory>

    /*
    *   Delete menu category from local.
    *   @return int
     */
    @Delete
    fun deleteMenuCategoryFromLocal(menuCategory: MenuCategory): Int

    /*
    *   Insert recipe posts to local database.
    *   @return long id
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipePostsToLocal(recipePosts: RecipePosts): Long

    /*
    *   Get all recipe posts from local database.
    *   @return list recipe posts
     */
    @Query("SELECT * FROM recipe_posts")
    fun getAllRecipePostsFromLocal(): List<RecipePosts>

    /*
    *   Get recipe posts by menu_cate_id from local database.
    *   @param int 'menuCateId'
    *   @return list recipe posts
     */
    @Query("SELECT * FROM recipe_posts WHERE recipe_posts.menu_cate_id=:menuCateId")
    fun getRecipePostsByIdFromLocal(menuCateId: Int): List<RecipePosts>

    /*
    *   Get recipe posts details by recipe_posts_id from local database.
    *   @param int 'recipePostsId'
    *   @return recipe posts
     */
    @Query("SELECT * FROM recipe_posts WHERE recipe_posts.recipe_post_id=:recipePostsId")
    fun getRecipePostsDetailsFromLocal(recipePostsId: Int): RecipePosts

    /*
    *   Delete recipe posts from local.
    *   @return int
     */
    @Delete
    fun deleteRecipePostsFromLocal(recipePosts: RecipePosts): Int
}