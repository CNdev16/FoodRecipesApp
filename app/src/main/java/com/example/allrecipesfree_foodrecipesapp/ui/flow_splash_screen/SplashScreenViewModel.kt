package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel(private val getAllDataUseCase: GetAllDataUseCase) : BaseViewModel() {

    val allRecipesData = MutableLiveData<List<ResultResponse>>()
    val version = MutableLiveData<String>(BuildConfig.VERSION_NAME)

    fun getAllRecipesData() {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getAllDataUseCase.execute(Unit) }) {
                is UseCaseResult.Success -> {
                    allRecipesData.value = result.data
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }
}