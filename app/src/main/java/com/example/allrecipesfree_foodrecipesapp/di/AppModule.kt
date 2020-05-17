package com.example.allrecipesfree_foodrecipesapp.di

import androidx.room.Room
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.MyFoodsViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.CategoriesRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.FavoriteRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchAllRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchRecipesViewModel
import com.example.core.BASE_URL
import com.example.core.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivityViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.MenuCategoriesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.FavoritesMenuViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen.SplashScreenViewModel
import com.example.core.DataRepository
import com.example.core.DataSource
import com.example.core.local.LocalDataSource
import com.example.core.local.migrations.MIGRATION_1_2
import com.example.core.remote.ApiService
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.ServiceEndPointInterface
import com.example.core.usecase.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val appModule = module {
    single { ApiService(BASE_URL) }
    single<DataSource> (StringQualifier("remote")){
        val apiService: ApiService = get()
        RemoteDataSource(
            apiService.getEndpointInterface(
                ServiceEndPointInterface::class.java
            )
        )
    }

    single<DataSource> (StringQualifier("local")){
        LocalDataSource(get())
    }

    single { DataRepository(get(StringQualifier("remote")), get(StringQualifier("local"))) }
    single {
        Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db")
            .addMigrations(MIGRATION_1_2)
            .build()
    }
    single { get<AppDataBase>().appDataBaseDao() }
}

val useCaseModule = module {
    factory { GetAllDataUseCase(get()) }
    factory { GetAllPostsMenuOnlyUseCase(get()) }
    factory { GetCountryCategoriesOnlyUseCase(get()) }
    factory { GetPostsMenuOnlyUseCase(get()) }
    factory { GetSubCategoriesOnlyUseCase(get()) }
    factory { AddAllDataUseCase(get()) }
    factory { GetAllDataFromDbUseCase(get()) }
    factory { DeleteAllDataFromDbUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { MenuCategoriesViewModel(get()) }
    viewModel { PostsMenuDetailViewModel(get()) }
    viewModel { FavoritesMenuViewModel(get()) }
    viewModel { AllRecipesViewModel(get()) }
    viewModel { CategoriesRecipesViewModel(get(), get()) }
    viewModel { MyFoodsViewModel(get()) }
    viewModel { FavoriteRecipesViewModel(get()) }
    viewModel { SearchRecipesViewModel(get()) }
    viewModel { SearchAllRecipesViewModel(get()) }
    viewModel { SplashScreenViewModel(get(), get(), get()) }
}

