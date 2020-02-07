package com.example.allrecipesfree_foodrecipesapp

import android.app.Application
import com.example.allrecipesfree_foodrecipesapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appModule))
        }
    }
}