package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentAllRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter.AllRecipesRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter.SearchRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRecipesFragment : BaseFragment<FragmentAllRecipesBinding>(), SearchItemsCallBack, ClearTextCallBack {

    private val viewModel: AllRecipesViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_all_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        val allRecipesRcAdapter = AllRecipesRcAdapter()
        binding.rcViewAllData.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = allRecipesRcAdapter
        }

        MainActivity.apply {
            setOnClickSearchItem(this@AllRecipesFragment)
            setOnClickClearText(this@AllRecipesFragment)
        }
    }

    override fun searchItemsCallBack(s: String?) {
        val adapterSearch = SearchRcAdapter()

        binding.rcViewSearchAllData.apply {
            visibility = View.VISIBLE
            slideFromTop()
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }
    }

    override fun clearTextCallBack() {
        if (binding.rcViewSearchAllData.isVisible) {
            binding.rcViewSearchAllData.apply {
                visibility = View.GONE
                slideFromBottom()
            }
        }
    }
}