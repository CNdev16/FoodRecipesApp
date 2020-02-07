package com.example.allrecipesfree_foodrecipesapp.di

import androidx.room.Room
import com.example.allrecipesfree_foodrecipesapp.BASE_URL
import com.example.allrecipesfree_foodrecipesapp.CONNECTION_TIME
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBaseDao
import com.example.allrecipesfree_foodrecipesapp.network.IServiceRepository
import com.example.allrecipesfree_foodrecipesapp.network.PostsService
import com.example.allrecipesfree_foodrecipesapp.network.ServiceRepositoryImpl
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivityViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.MenuCategoriesViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.FavoritesMenuViewModel
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        createWebService<PostsService>(
            provideHttpClient(),
            RxJava2CallAdapterFactory.create(),
            BASE_URL
        )
    }

    single { Room.databaseBuilder(androidApplication(), AppDataBase::class.java, "app-db").allowMainThreadQueries().build() }
    single { get<AppDataBase>().appDataBaseDao() }

    factory<IServiceRepository> { ServiceRepositoryImpl(get()) }

    viewModel { MainActivityViewModel(get()) }
    viewModel { MenuCategoriesViewModel(get()) }
    viewModel { PostsMenuDetailViewModel(get()) }
    viewModel { FavoritesMenuViewModel(get()) }
}

fun provideHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
        .connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
        .readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", UUID.randomUUID().toString())
                .build()
            chain.proceed(newRequest)
        }
    return builder.build()
}

inline fun <reified T> createWebService(
    client: OkHttpClient,
    factory: CallAdapter.Factory,
    baseUrl: String
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient()
                    .setPrettyPrinting()
                    .create()
            )
        )
        .addCallAdapterFactory(factory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build().create(T::class.java)
}