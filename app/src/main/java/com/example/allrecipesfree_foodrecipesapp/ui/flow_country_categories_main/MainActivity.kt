package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityMainBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_about.AboutActivity
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.adapter.CountryRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.adapter.SearchRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.MenuCategoriesActivity
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.FavoritesMenuActivity
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.Utils
import com.example.allrecipesfree_foodrecipesapp.utility.hideKeyboard
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var countryRcAdapter: CountryRcAdapter
    private lateinit var searchRcAdapter: SearchRcAdapter
    private lateinit var sheetBehavior: BottomSheetBehavior<RelativeLayout>

    override var layoutResource: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        subscribeLiveData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.app_name)
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    private fun subscribeLiveData() {
        binding.viewModel = viewModel
        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))
        viewModel.fetchCountryCategories(0)

        viewModel.allCountryCategories.observe(this, Observer {
            DialogUtils.disMissDialog()
            countryRcAdapter = CountryRcAdapter(it, this)

            binding.rcView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = countryRcAdapter
            }
            countryRcAdapter.setOnClickCountry(object : CountryRcAdapter.OnClickCountry {
                override fun onClickCountry(country: ServiceResponse, position: Int) {

                    startActivity(
                        Intent(
                            this@MainActivity,
                            MenuCategoriesActivity::class.java
                        ).putExtra("id", country.id)
                    )
                    pageTransition()
                }
            })
            setupSearch()
            setupButtonMenu()
        })
    }

    private fun setupSearch() {
        sheetBehavior = BottomSheetBehavior.from(binding.search.bottomSheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.search.layoutResult.hideKeyboard()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        })
    }

    private fun toggleBottomSheet() {
        when (sheetBehavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            else -> {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun toggleBottomSheetSwipe() {
        when (sheetBehavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun toggleBottomSheetBack() {
        when (sheetBehavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            else -> {
                finishAffinity()
            }
        }
    }

    private fun setupButtonMenu() {
        binding.layoutFav.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FavoritesMenuActivity::class.java
                )
            )
            toggleBottomSheetSwipe()
            pageTransition()
        }
        binding.search.layoutTextSearch.setOnClickListener { toggleBottomSheet() }
        binding.layoutAbout.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AboutActivity::class.java
                )
            )
            toggleBottomSheetSwipe()
            pageTransition()
        }

        binding.search.imgBtnSearch.setOnClickListener { if (binding.search.edtSearch.text.isNotEmpty()) searchResult() }

    }

    private fun searchResult() {

        binding.search.progress.visibility = View.VISIBLE
        viewModel.searchPostsMenu(binding.search.edtSearch.text.toString())

        viewModel.allPostsMenuBySearch.observe(this, Observer {
            binding.search.progress.visibility = View.GONE

            binding.search.layoutResult.hideKeyboard()

            if (it.isNotEmpty()){
                binding.search.rcViewSearchResult.visibility = View.VISIBLE
                binding.search.tvEmpty.visibility = View.GONE
                searchRcAdapter = SearchRcAdapter(it, this)

                binding.search.rcViewSearchResult.apply {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    adapter = searchRcAdapter
                }
                searchRcAdapter.setOnClickResult(object : SearchRcAdapter.OnClickResult {
                    override fun onClickResult(result: ServiceResponse, position: Int) {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                PostsMenuDetailActivity::class.java
                            ).putExtra("id", result.id)
                        )
                        pageTransition()
                    }

                })
            }else{
                binding.search.rcViewSearchResult.visibility = View.GONE
                binding.search.tvEmpty.visibility = View.VISIBLE
                binding.search.tvEmpty.text = getString(R.string.result_not_found)
            }
        })
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        toggleBottomSheetBack()
    }
}
