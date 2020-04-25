package com.example.allrecipesfree_foodrecipesapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.allrecipesfree_foodrecipesapp.di.appModule
import com.example.allrecipesfree_foodrecipesapp.di.useCaseModule
import com.example.allrecipesfree_foodrecipesapp.di.viewModelModule
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Mitr-Regular.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appModule, viewModelModule, useCaseModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}