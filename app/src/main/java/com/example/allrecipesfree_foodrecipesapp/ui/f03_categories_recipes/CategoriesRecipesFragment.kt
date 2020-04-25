package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import android.os.Bundle
import android.view.View
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentCategoriesRecipesBinding

class CategoriesRecipesFragment ():BaseFragment<FragmentCategoriesRecipesBinding>(){
    override var layoutResource: Int = R.layout.fragment_categories_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}