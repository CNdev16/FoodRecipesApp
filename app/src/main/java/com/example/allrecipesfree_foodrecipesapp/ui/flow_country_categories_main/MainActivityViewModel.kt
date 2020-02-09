package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.allrecipesfree_foodrecipesapp.base.BaseViewModel
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.network.IServiceRepository
import com.example.allrecipesfree_foodrecipesapp.network.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val iServiceRepository: IServiceRepository) : BaseViewModel() {

    //ประกาศตัวแปร รอรับค่า result.
    val allCountryCategories = MutableLiveData<List<ServiceResponse>>()
    val allPostsMenuBySearch = MutableLiveData<List<ServiceResponse>>()

    //ฟังก์ชันค้นหา "หมวดหมู่ประเทศ" ส่ง param [parentNo] = 0
    fun fetchCountryCategories(parentNo: Int) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getCountryCategories(parentNo) }) {
                is UseCaseResult.Success -> {
                    allCountryCategories.postValue(result.data)
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }

    //ฟังก์ชันค้นหา "posts" ใช้ param [s] มาใช้ในการ filter ข้อมูล
    fun searchPostsMenu(s: String) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { iServiceRepository.getSearchPostsMenu() }) {
                is UseCaseResult.Success -> {
                    allPostsMenuBySearch.postValue(result.data.filter { response -> response.title!!.rendered!!.contains(s) })
                }
                is UseCaseResult.Error -> {
                }
            }
        }
    }
}