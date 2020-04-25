package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.core.UseCaseResult
import com.example.core.data.ResultResponse
import com.example.core.usecase.GetAllDataCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllRecipesViewModel(private val getAllDataCase: GetAllDataCase) : BaseViewModel() {

    val allData = MutableLiveData<List<ResultResponse>>()

    fun getAllData() {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getAllDataCase.execute(Unit) }) {
                is UseCaseResult.Success -> {
                    allData.value = result.data
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }

}