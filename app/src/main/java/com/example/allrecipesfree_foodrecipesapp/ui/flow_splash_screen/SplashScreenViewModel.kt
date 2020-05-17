package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.UseCaseResult
import com.example.core.data.CountryCategory
import com.example.core.usecase.AddAllDataUseCase
import com.example.core.usecase.DeleteAllDataFromDbUseCase
import com.example.core.usecase.GetAllDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val addAllDataUseCase: AddAllDataUseCase,
    private val deleteAllDataFromDbUseCase: DeleteAllDataFromDbUseCase
) : BaseViewModel() {

    val allRecipesData = MutableLiveData<List<CountryCategory>>()
    val addPostsToDb = MutableLiveData<Long>()
    val deletePostsFromDb = MutableLiveData<Int>()
    val version = MutableLiveData<String>(BuildConfig.VERSION_NAME)

    fun getAllRecipesData(isInternetConnected: Boolean) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getAllDataUseCase.execute(Unit, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    //allRecipesData.value = result.data
                    showLoading.value = false
                    allRecipesData.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }

    fun addAllRecipesData(countryCategory: CountryCategory, isInternetConnected: Boolean) {
        viewModelScope.launch {
            deleteAllDataFromDbUseCase.execute(countryCategory, isInternetConnected)
            when (val result = withContext(Dispatchers.IO) { addAllDataUseCase.execute(countryCategory, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    addPostsToDb.value = result.data
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                }
            }
        }
    }

    fun deleteAllRecipesData(countryCategory: CountryCategory, isInternetConnected: Boolean) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { deleteAllDataFromDbUseCase.execute(countryCategory, isInternetConnected) }) {
                is UseCaseResult.Success -> {
                    showLoading.value = false
                    deletePostsFromDb.value = result.data
                    Log.d("test s", "${result.data}")
                }
                is UseCaseResult.Error -> {
                    showLoading.value = false
                    handleError.value = result.responseMessage
                    logD(result.responseMessage)
                }
            }
        }
    }
}