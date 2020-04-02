package com.example.allrecipesfree_foodrecipesapp.di

import androidx.room.Room
import com.example.core.BASE_URL
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivityViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.MenuCategoriesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.FavoritesMenuViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailViewModel
import com.example.core.ApiService
import com.example.core.RemoteRepositoryImpl
import com.example.core.ServiceEndPointInterface
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiService(BASE_URL) }
    single {
        val apiService: ApiService = get()
        RemoteRepositoryImpl(apiService.getEndpointInterface(ServiceEndPointInterface::class.java))
    }
    single { Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db").allowMainThreadQueries().build() }
    single { get<AppDataBase>().appDataBaseDao() }

    viewModel { MainActivityViewModel(get()) }
    viewModel { MenuCategoriesViewModel(get()) }
    viewModel { PostsMenuDetailViewModel(get()) }
    viewModel { FavoritesMenuViewModel(get()) }
}

