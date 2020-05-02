package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyFoodsViewModel(private val getAllDataUseCase: GetAllDataUseCase) : BaseViewModel() {
}