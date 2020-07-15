package com.example.allrecipesfree_foodrecipesapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityMainBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.MyFoodsFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllRecipesFragment
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

    private lateinit var countryRcAdapter: CountryRcAdapter
    private lateinit var searchRcAdapter: SearchRcAdapter
    private lateinit var sheetBehavior: BottomSheetBehavior<RelativeLayout>
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

        setupToolbar()
        //setBottomNavigation()

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
        viewModel.getAllDataFromService(isInternetConnected())

        viewModel.allDataFromService.observe(this, Observer {
            logD("Get Data Successfully... ${Gson().toJson(it)}")

            it.forEach { i ->
                viewModel.insertCountryData(i, isInternetConnected())
            }

            it.forEach { i ->
                i.menuCategoryList!!.forEach { j ->
                    viewModel.insertMenuData(j, isInternetConnected())
                }
            }

            it.forEach { i ->
                i.menuCategoryList!!.forEach { j ->
                    j.recipePostsList!!.forEach { k ->
                        viewModel.insertRecipeData(k, isInternetConnected())
                    }
                }
            }

            viewModel.insertCountryToLocal.observe(this, Observer {
                logD("Insert Country Data Successfully...")
            })

            viewModel.insertMenuToLocal.observe(this, Observer {
                logD("Insert Menu Data Successfully...")
            })

            viewModel.insertRecipeToLocal.observe(this, Observer {
                logD("Insert Recipe Data Successfully...")
            })

            setBottomNavigation()
        })
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuMyFood -> {
                    goToMyFoodMenu()
                }
                R.id.menuAllRecipes -> {
                    goToAllRecipesMenu()
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
    }

    private fun goToAllRecipesMenu() {
        addFragment(R.id.contentContainer, AllRecipesFragment())
        customActionbar.apply {
            setTextHeader("All Recipes")
            showSearchIcon(true)
            search(false)
        }
        setStateMenu(R.id.menuAllRecipes)
        setFabStatus(false)
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
                //fadeIn()
            }
        } else {
            if (binding.fab.isVisible) {
                binding.fab.apply {
                    gone()
                    //()
                }
            }
        }
    }

    //    private fun subscribeLiveData() {
//
//        binding.viewModel = viewModel
//
//        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))
//
//        viewModel.fetchCountryCategories(0)
//
//        viewModel.allCountryCategories.observe(this, Observer {
//            DialogUtils.disMissDialog()
//
//            countryRcAdapter = CountryRcAdapter(it, this)
//
//            binding.rcView.apply {
//                setHasFixedSize(true)
//                layoutManager = GridLayoutManager(this@MainActivity, 2)
//                adapter = countryRcAdapter
//            }
//            countryRcAdapter.setOnClickCountry(object : CountryRcAdapter.OnClickCountry {
//                override fun onClickCountry(country: ServiceResponse, position: Int) {
//
//                    startActivity(
//                        Intent(
//                            this@MainActivity,
//                            MenuCategoriesActivity::class.java
//                        ).putExtra("id", country.id)
//                    )
//                    pageTransition()
//                }
//            })
//            setupSearch()
//            setupButtonMenu()
//        })
//    }
//
//    private fun setupSearch() {
//
//        sheetBehavior = BottomSheetBehavior.from(binding.search.bottomSheet)
//        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//            }
//
//            @SuppressLint("SwitchIntDef")
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                    }
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        binding.search.layoutResult.hideKeyboard()
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                    }
//                }
//            }
//        })
//    }
//
//    private fun toggleBottomSheet() {
//        when (sheetBehavior.state) {
//            BottomSheetBehavior.STATE_EXPANDED -> {
//                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            }
//            else -> {
//                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//    }
//
//    private fun toggleBottomSheetSwipe() {
//        when (sheetBehavior.state) {
//            BottomSheetBehavior.STATE_EXPANDED -> {
//                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            }
//        }
//    }
//
//    private fun toggleBottomSheetBack() {
//        when (sheetBehavior.state) {
//            BottomSheetBehavior.STATE_EXPANDED -> {
//                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//            }
//            else -> {
//                finishAffinity()
//            }
//        }
//    }
//
//    private fun setupButtonMenu() {
//        binding.layoutFav.onClickApplicationListener {
//            startActivity(
//                Intent(
//                    this,
//                    FavoritesMenuActivity::class.java
//                )
//            )
//            toggleBottomSheetSwipe()
//            pageTransition()
//        }
//        binding.search.layoutTextSearch.onClickApplicationListener { toggleBottomSheet() }
//        binding.layoutAbout.onClickApplicationListener {
//            startActivity(
//                Intent(
//                    this,
//                    AboutActivity::class.java
//                )
//            )
//            toggleBottomSheetSwipe()
//            pageTransition()
//        }
//
//        binding.search.imgBtnSearch.onClickApplicationListener { if (binding.search.edtSearch.text.isNotEmpty()) searchResult() }
//
//    }
//
//    private fun searchResult() {
//
//        binding.search.progress.visibility = View.VISIBLE
//        viewModel.searchPostsMenu(binding.search.edtSearch.text.toString())
//
//        viewModel.allPostsMenuBySearch.observe(this, Observer {
//            binding.search.progress.visibility = View.GONE
//
//            binding.search.layoutResult.hideKeyboard()
//
//            if (it.isNotEmpty()){
//                binding.search.rcViewSearchResult.visibility = View.VISIBLE
//                binding.search.tvEmpty.visibility = View.GONE
//                searchRcAdapter = SearchRcAdapter(it, this)
//
//                binding.search.rcViewSearchResult.apply {
//                    setHasFixedSize(true)
//                    layoutManager =
//                        LinearLayoutManager(
//                            this@MainActivity,
//                            LinearLayoutManager.VERTICAL,
//                            false
//                        )
//                    adapter = searchRcAdapter
//                }
//                searchRcAdapter.setOnClickResult(object : SearchRcAdapter.OnClickResult {
//                    override fun onClickResult(result: ServiceResponse, position: Int) {
//                        startActivity(
//                            Intent(
//                                this@MainActivity,
//                                PostsMenuDetailActivity::class.java
//                            ).putExtra("id", result.id)
//                        )
//                        pageTransition()
//                    }
//
//                })
//            }else{
//                binding.search.rcViewSearchResult.visibility = View.GONE
//                binding.search.tvEmpty.visibility = View.VISIBLE
//                binding.search.tvEmpty.text = getString(R.string.result_not_found)
//            }
//        })
//    }

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