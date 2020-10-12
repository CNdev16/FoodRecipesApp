package com.example.allrecipesfree_foodrecipesapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityMainBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.MyFoodsFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllMenuFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.CategoriesRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.FavoriteRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchAllRecipesActivity
import com.example.allrecipesfree_foodrecipesapp.ui.main.adapter.CountryRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.main.adapter.SearchRcAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), CustomActionbar.OnClickItemsToolBar {

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var customActionbar: CustomActionbar

    override var layoutResource: Int = R.layout.activity_main

    companion object {
        private var searchItemsCallBack: SearchItemsCallBack? = null
        private var clearTextCallBack: ClearTextCallBack? = null

        fun setOnClickSearchItem(searchItemsCallBack: SearchItemsCallBack) {
            this.searchItemsCallBack = searchItemsCallBack
        }

        fun setOnClickClearText(clearTextCallBack: ClearTextCallBack) {
            this.clearTextCallBack = clearTextCallBack
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        customActionbar = CustomActionbar(
            this,
            this
            , supportActionBar
        )
        customActionbar.setOnClickItemsToolBar(this)
    }

    override fun subscribeLiveData() {
        setupToolbar()
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuMyFood -> {
                    goToMyFoodMenu()
                }
                R.id.menuAllmenu -> {
                    goToAllMenuMenu()
                }
                R.id.menuCategories -> {
                    goToCategoryMenu()
                }
                R.id.menuFavorite -> {
                    goToFavoriteMenu()
                }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.menuMyFood

        binding.fab.onClickApplicationListener {
            startActivity(
                Intent(
                    this,
                    SearchAllRecipesActivity::class.java
                )
            )
            //pageTransition()
        }
    }

    private fun goToMyFoodMenu(){
        addFragment(R.id.contentContainer, MyFoodsFragment())
        customActionbar.apply {
            setTextHeader("My Foods")
            showSearchIcon(false)
            search(false)
        }
        setStateMenu(R.id.menuMyFood)
        setFabStatus(true)
        setToolbarVisible(false)
    }

    private fun goToAllMenuMenu() {
        addFragment(R.id.contentContainer, AllMenuFragment())
        customActionbar.apply {
            setTextHeader("All Recipes")
            showSearchIcon(true)
            search(false)
        }
        setStateMenu(R.id.menuAllmenu)
        setFabStatus(false)
        setToolbarVisible()
    }

    private fun goToCategoryMenu() {
        addFragment(R.id.contentContainer, CategoriesRecipesFragment())
        customActionbar.apply {
            setTextHeader("Categories")
            showSearchIcon(true)
            search(false)
        }
        setStateMenu(R.id.menuCategories)
        setFabStatus(false)
        setToolbarVisible()
    }

    private fun goToFavoriteMenu(){
        addFragment(R.id.contentContainer, FavoriteRecipesFragment())
        customActionbar.apply {
            setTextHeader("Favorite")
            showSearchIcon(true)
            search(false)
        }
        setStateMenu(R.id.menuFavorite)
        setFabStatus(false)
        setToolbarVisible()
    }

    fun setToolbarVisible(isVisible: Boolean = true){
        binding.toolbar.visibility = when(isVisible){
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun setStateMenu(id: Int) {
        val mnSize: Int = binding.bottomNavigation.menu.size
        val mn = binding.bottomNavigation.menu
        for (i in 0 until mnSize) {
            val item = mn.getItem(i).itemId
            mn.getItem(i).isEnabled = item != id
        }
    }

    private fun setFabStatus(isShow: Boolean) {
        if (isShow) {
            binding.fab.apply {
                visible()
            }
        } else {
            if (binding.fab.isVisible) {
                binding.fab.apply {
                    gone()
                }
            }
        }
    }

    override fun loading() {
        viewModel.showLoading.observe(this, Observer {
            if (it) DialogUtils.showProgressDialog(
                this,
                getString(R.string.progress_msg)
            ) else DialogUtils.disMissDialog()
        })
    }

    override fun handleError() {
        viewModel.handleError.observe(this, Observer {
            DialogUtils.showDialogOneButton(
                this,
                it[0],
                it[1],
                "Ok",
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                    }
                }
            )
        })
    }

    override fun onClickItemRight(text: String?) {
        Log.d("printtt", "onClickItemRight")
        searchItemsCallBack?.searchItemsCallBack(text)
    }

    override fun onClickItemLeft() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickClearText() {
        clearTextCallBack?.clearTextCallBack()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //toggleBottomSheetBack()
    }
}
