package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import android.os.Bundle
import android.view.View
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentMyFoodsBinding

class MyFoodsFragment : BaseFragment<FragmentMyFoodsBinding>(){
    override var layoutResource: Int = R.layout.fragment_my_foods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}