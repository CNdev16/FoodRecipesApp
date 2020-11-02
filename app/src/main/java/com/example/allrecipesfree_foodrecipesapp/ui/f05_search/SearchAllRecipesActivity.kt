package com.example.allrecipesfree_foodrecipesapp.ui.f05_search

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySearchAllRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.utility.CustomActionbar
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchAllRecipesActivity : BaseActivity<ActivitySearchAllRecipesBinding>(),
    CustomActionbar.OnClickItemsToolBar {

    private val viewModel: SearchAllRecipesViewModel by viewModel()
    private lateinit var customActionbar: CustomActionbar

    override var layoutResource: Int = R.layout.activity_search_all_recipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        binding.viewModel = viewModel
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        customActionbar = CustomActionbar(
            this,
            this, supportActionBar
        )
        customActionbar.apply {
            setTextHeader("Search Your Recipes")
            showBackIcon(true)
            showSearchIcon(true)
            search(false)
            setInputBox(
                SearchAllRecipesActivity::class.java.simpleName,
                R.drawable.round_menu_book_white_18dp
            )
            setOnClickItemsToolBar(this@SearchAllRecipesActivity)
        }
    }

    override fun onClickItemRight(text: String?) {

        viewModel.getAllDataFromService(isInternetConnected())
        viewModel.allDataFromService.observe(this, Observer {

        })

    }

    override fun onClickItemLeft() {
        onBackPressed()
    }

    override fun onClickClearText() {
    }

}