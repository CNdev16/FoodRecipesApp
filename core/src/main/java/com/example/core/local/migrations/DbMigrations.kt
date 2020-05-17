package com.example.core.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `country_category` (`country_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `country_cate_id` INTEGER NOT NULL, `country_cate_name` TEXT NOT NULL, `country_cate_img` TEXT NOT NULL, `menu_category_list` TEXT)"
        )

        database.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_country_category_country_cate_id` ON `country_category` (`country_cate_id`)"
        )

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `menu_category` (`menu_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `country_cate_id` INTEGER NOT NULL, `country_cate_name` TEXT NOT NULL, `menu_cate_id` INTEGER NOT NULL, `menu_cate_name` TEXT NOT NULL, `menu_cate_img` TEXT NOT NULL, `recipe_posts_list` TEXT, FOREIGN KEY(`menu_id`) REFERENCES `country_category`(`country_id`) ON UPDATE NO ACTION ON DELETE CASCADE )"
        )

        database.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_menu_category_menu_cate_id` ON `menu_category` (`menu_cate_id`)"
        )

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `recipe_posts` (`recipe_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `country_cate_id` INTEGER NOT NULL, `country_cate_name` TEXT NOT NULL, `menu_cate_id` INTEGER NOT NULL, `menu_cate_name` TEXT NOT NULL, `recipe_post_id` INTEGER NOT NULL, `recipe_post_author` TEXT NOT NULL, `recipe_post_name` TEXT NOT NULL, `recipe_post_date` TEXT NOT NULL, `recipe_post_title` TEXT NOT NULL, `recipe_post_content` TEXT NOT NULL, `recipe_post_status` TEXT NOT NULL, `recipe_post_type` TEXT NOT NULL, `recipe_post_img` TEXT NOT NULL, `recipe_post_modified` TEXT NOT NULL, `recipe_post_favorite` INTEGER NOT NULL, FOREIGN KEY(`recipe_id`) REFERENCES `menu_category`(`menu_id`) ON UPDATE NO ACTION ON DELETE CASCADE )"
        )

        database.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_recipe_posts_recipe_post_id` ON `recipe_posts` (`recipe_post_id`)"
        )
    }
}