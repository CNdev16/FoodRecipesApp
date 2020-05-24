package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.InsertCountryCategoryToLocalUseCase
import com.example.core.usecase.DeleteCountryCategoryFromLocalUseCase
import com.example.core.usecase.GetAllDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel : BaseViewModel() {
    val version = MutableLiveData<String>(BuildConfig.VERSION_NAME)
}