package com.example.allrecipesfree_foodrecipesapp.ui.f05_search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentSearchRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter.SearchRcAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRecipesFragment : BaseFragment<FragmentSearchRecipesBinding>() {

    private val viewModel: SearchRecipesViewModel by viewModel()
    override var layoutResource: Int = R.layout.fragment_search_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        val adapterSearch = SearchRcAdapter()

        binding.rcSearchList.apply {
            visibility = View.VISIBLE

            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }
    }
}