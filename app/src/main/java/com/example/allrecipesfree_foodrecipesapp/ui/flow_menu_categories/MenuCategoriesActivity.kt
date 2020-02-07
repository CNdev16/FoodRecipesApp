package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.data.Favorite
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityMenuCategoriesBinding
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter.PostsMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuCategoriesActivity : BaseActivity<ActivityMenuCategoriesBinding>() {

    private val viewModel: MenuCategoriesViewModel by viewModel()
    private lateinit var postsMenuRcAdapter: PostsMenuRcAdapter
    private val appData: AppDataBase by inject()
    private lateinit var listServiceResponse: List<ServiceResponse>

    override var layoutResource: Int = R.layout.activity_menu_categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id: Int = intent.getIntExtra("id", -1)

        setupToolbar()

        subscribeLiveData(id)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = "All Recipes Free - Food Recipes App"
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    private fun subscribeLiveData(id: Int) {
        binding.viewModel = viewModel

        viewModel.fetchMenuCategories(id)
        DialogUtils.showProgressDialog(this, "Fetching data, please with...")
        viewModel.allMenuCategories.observe(this, Observer {
            DialogUtils.disMissDialog()
            if (it.isNotEmpty()) {
                listServiceResponse = it
                setupListMenu(it)
            } else {
                binding.tab.visibility = View.GONE
                binding.rcView.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
                binding.tvEmpty.text = "Category is empty."
            }
        })
    }

    private fun setupListMenu(it: List<ServiceResponse>?) {
        binding.tab.visibility = View.VISIBLE
        binding.rcView.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        it!!.forEach { s ->
            binding.tab.addTab(binding.tab.newTab().setText(s.name))
        }

        DialogUtils.showProgressDialog(this, "Fetching data, please with...")
        viewModel.fetchPostsMenu(it[binding.tab.selectedTabPosition].id!!)

        viewModel.allPostsMenu.observe(this@MenuCategoriesActivity, Observer {
            DialogUtils.disMissDialog()

            if (it.isNotEmpty()) {
                setupRecyclerView(it)
            } else {
                binding.rcView.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
                binding.tvEmpty.text = "Menu is empty."
            }
        })

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                DialogUtils.showProgressDialog(
                    this@MenuCategoriesActivity,
                    "Fetching data, please with..."
                )
                viewModel.fetchPostsMenu(it[binding.tab.selectedTabPosition].id!!)
            }
        })
    }

    private fun setupRecyclerView(it: List<ServiceResponse>?) {
        binding.rcView.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        postsMenuRcAdapter =
            PostsMenuRcAdapter(
                updateModel(
                    it!!,
                    appData.appDataBaseDao().getFavoriteMenu()
                ), this
            )
        binding.rcView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    this@MenuCategoriesActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = postsMenuRcAdapter
        }
        postsMenuRcAdapter.setOnClickPostsMenu(object : PostsMenuRcAdapter.OnClickPostsMenu {
            override fun onClickPostsMenu(postsMenu: ServiceResponse, position: Int) {
                startActivity(
                    Intent(
                        this@MenuCategoriesActivity,
                        PostsMenuDetailActivity::class.java
                    ).putExtra("id", postsMenu.id)
                )
                pageTransition()
            }

            override fun onClickFavoriteMenu(postsMenu: ServiceResponse, position: Int) {
                val t = appData.appDataBaseDao().getFavoriteMenu()
                    .filter { p -> (p.id == postsMenu.id) && (postsMenu.favoriteStatus == true) }
                if (t.isEmpty()) {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            postsMenu.id!!,
                            postsMenu.title!!.rendered!!,
                            true
                        )
                    )
                    postsMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                } else {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            postsMenu.id!!,
                            postsMenu.title!!.rendered!!,
                            false
                        )
                    )
                    postsMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                }
            }

        })
    }

    private fun updateModel(
        serviceResponse: List<ServiceResponse>,
        favoriteMenu: List<Favorite>
    ): List<ServiceResponse> {
        favoriteMenu.forEach { f ->
            serviceResponse.find { it.id == f.id }?.favoriteStatus = f.status
        }
        return serviceResponse
    }

}