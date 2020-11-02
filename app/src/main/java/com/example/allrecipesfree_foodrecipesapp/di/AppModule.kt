package com.example.allrecipesfree_foodrecipesapp.di

import androidx.room.Room
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.MyFoodsViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllMenuViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.CategoriesRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.FavoriteRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchAllRecipesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.f06_recipe_detail.RecipeDetailViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.main.MainActivityViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.splash_screen.SplashScreenViewModel
import com.example.core.BASE_URL
import com.example.core.DataRepository
import com.example.core.DataSource
import com.example.core.local.AppDataBase
import com.example.core.local.LocalDataSource
import com.example.core.remote.ApiService
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.ApiEndPointInterface
import com.example.core.usecase.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val appModule = module {
    single { ApiService(BASE_URL) }
    single<DataSource>(StringQualifier("remote")) {
        val apiService: ApiService = get()
        RemoteDataSource(
            apiService.getEndpointInterface(
                ApiEndPointInterface::class.java
            )
        )
    }

    single<DataSource>(StringQualifier("local")) {
        LocalDataSource(get())
    }

    single { DataRepository(get(StringQualifier("remote")), get(StringQualifier("local"))) }
    single {
        Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().appDataBaseDao() }
}

val useCaseModule = module {
    factory { GetAllDataUseCase(get()) }
    factory { GetRecipePostsUseCase(get()) }
    factory { GetCountryCategoryUseCase(get()) }
    factory { GetMenuCategoryUseCase(get()) }
    factory { GetRecipePostsDetailsUseCase(get()) }
    factory { InsertCountryCategoryToLocalUseCase(get()) }
    factory { DeleteCountryCategoryFromLocalUseCase(get()) }
    factory { InsertMenuCategoryToLocalUseCase(get()) }
    factory { DeleteMenuCategoryFromLocalUseCase(get()) }
    factory { InsertRecipePostsToLocalUseCase(get()) }
    factory { DeleteRecipePostsFromLocalUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { AllMenuViewModel(get()) }
    viewModel { CategoriesRecipesViewModel(get(), get()) }
    viewModel { MyFoodsViewModel(get()) }
    viewModel { FavoriteRecipesViewModel(get()) }
    viewModel { SearchAllRecipesViewModel(get()) }
    viewModel { SplashScreenViewModel(get(), get(), get(), get()) }
    viewModel { RecipeDetailViewModel() }
}

