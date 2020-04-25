package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentAllRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter.AllRecipesRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.SearchItemsCallBack
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRecipesFragment : BaseFragment<FragmentAllRecipesBinding>(), SearchItemsCallBack{

    private val viewModel: AllRecipesViewModel by viewModel()
    private var searchItemsCallBack: SearchItemsCallBack? = null

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

        MainActivity.setOnClickSearchItem(this)
    }

    override fun searchItemsCallBack(s: String?) {
        Log.d("printtt ", s!!)
    }
}