package com.example.allrecipesfree_foodrecipesapp.ui.splash_screen

import androidx.lifecycle.MutableLiveData
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel

class SplashScreenViewModel : BaseViewModel() {
    val version = MutableLiveData<String>(BuildConfig.VERSION_NAME)
}