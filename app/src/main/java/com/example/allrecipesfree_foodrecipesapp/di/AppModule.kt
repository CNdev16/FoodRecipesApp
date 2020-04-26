package com.example.allrecipesfree_foodrecipesapp.di

import androidx.room.Room
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllRecipesViewModel
import com.example.core.BASE_URL
import com.example.core.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivityViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.MenuCategoriesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.FavoritesMenuViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailViewModel
import com.example.core.DataRepository
import com.example.core.DataSource
import com.example.core.remote.ApiService
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.ServiceEndPointInterface
import com.example.core.usecase.GetAllDataUseCase
import com.example.core.usecase.GetAllPostMenuUseCase
import com.example.core.usecase.GetPostMenuUseCase
import com.example.core.usecase.GetSubCategoriesUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiService(BASE_URL) }
    single<DataSource> {
        val apiService: ApiService = get()
        RemoteDataSource(
            apiService.getEndpointInterface(
                ServiceEndPointInterface::class.java
            )
        )
    }

    single { DataRepository(get()) }
    single {
        Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db")
            .allowMainThreadQueries().build()
    }
    single { get<AppDataBase>().appDataBaseDao() }
}

val useCaseModule = module {
    factory { GetAllDataUseCase(get()) }
//    factory { GetAllPostMenuUseCase(get()) }
//    factory { GetSubCategoriesUseCase(get()) }
//    factory { GetPostMenuUseCase(get()) }
//    factory { GetSubCategoriesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { MenuCategoriesViewModel(get()) }
    viewModel { PostsMenuDetailViewModel(get()) }
    viewModel { FavoritesMenuViewModel(get()) }
    viewModel { AllRecipesViewModel(get()) }
}

