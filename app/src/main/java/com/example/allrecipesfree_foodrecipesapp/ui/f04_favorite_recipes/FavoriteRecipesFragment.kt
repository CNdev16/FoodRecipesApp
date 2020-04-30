package com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentFavoriteRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.adapter.FavoriteRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.SearchItemsCallBack
import com.example.allrecipesfree_foodrecipesapp.utility.replaceFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteRecipesFragment() : BaseFragment<FragmentFavoriteRecipesBinding>(), SearchItemsCallBack {

    private val viewModel: FavoriteRecipesViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_favorite_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        val favoriteRcAdapter = FavoriteRcAdapter()
        binding.rcViewFav.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = favoriteRcAdapter
        }

    }

    override fun searchItemsCallBack(s: String?) {
    }
}