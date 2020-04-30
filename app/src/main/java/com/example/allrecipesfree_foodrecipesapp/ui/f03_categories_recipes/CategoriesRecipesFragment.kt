package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentCategoriesRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.CountryRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.RecipesRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.SearchItemsCallBack
import com.example.allrecipesfree_foodrecipesapp.utility.replaceFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesRecipesFragment : BaseFragment<FragmentCategoriesRecipesBinding>(), SearchItemsCallBack {

    private val viewModel: CategoriesRecipesViewModel by viewModel()
    private lateinit var countryRcAdapter: CountryRcAdapter
    private lateinit var recipesRcAdapter: RecipesRcAdapter

    override var layoutResource: Int = R.layout.fragment_categories_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        countryRcAdapter = CountryRcAdapter()
        binding.rcCountry.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = countryRcAdapter
        }

        recipesRcAdapter = RecipesRcAdapter()
        binding.rcRecipe.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(
                activity,
                2
            )
            adapter = recipesRcAdapter
        }

    }

    override fun searchItemsCallBack(s: String?) {
    }
}