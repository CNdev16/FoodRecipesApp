package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentCategoriesRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.CountryRcAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesRecipesFragment : BaseFragment<FragmentCategoriesRecipesBinding>() {

    private val viewModel: CategoriesRecipesViewModel by viewModel()
    private lateinit var countryRcAdapter: CountryRcAdapter

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

    }
}