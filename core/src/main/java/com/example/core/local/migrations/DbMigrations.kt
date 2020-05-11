package com.example.core.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS result_response (" +
                    "    `list_parent_cate_id` INT," +
                    "    `list_parent_cate_name` TEXT," +
                    "    `list_parent_cate_img` TEXT," +
                    "    `list_sub_cate_list_parent_id` INT," +
                    "    `list_sub_cate_list_parent_name` TEXT," +
                    "    `list_sub_cate_list_sub_cate_id` INT," +
                    "    `list_sub_cate_list_sub_cate_name` TEXT," +
                    "    `list_sub_cate_list_sub_cate_img` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_id` INT," +
                    "    `list_sub_cate_list_posts_list_post_author` INT," +
                    "    `list_sub_cate_list_posts_list_post_name` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_date` DATETIME," +
                    "    `list_sub_cate_list_posts_list_post_title` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_content` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_status` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_type` TEXT," +
                    "    `list_sub_cate_list_posts_list_post_img` TEXT" +
                    "     PRIMARY KEY(`list_parent_cate_id`))"
        )
    }

}