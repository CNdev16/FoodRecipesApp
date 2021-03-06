package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite

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
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityFavoritesMenuBinding
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter.PostsMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.adapter.FavoriteMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesMenuActivity : BaseActivity<ActivityFavoritesMenuBinding>() {

    private val viewModel: FavoritesMenuViewModel by viewModel()
    private lateinit var favoriteMenuRcAdapter: FavoriteMenuRcAdapter
    private val appData: AppDataBase by inject()

    override var layoutResource: Int = R.layout.activity_favorites_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        setupSubscribeLiveData()
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

    private fun setupSubscribeLiveData() {
        binding.viewModel = viewModel

        DialogUtils.showProgressDialog(
            this,
            getString(R.string.progress_msg)
        )
        viewModel.fetchAllPostsMenu()

        viewModel.allPostsMenu.observe(this, Observer {
            DialogUtils.disMissDialog()

            if (it.isNotEmpty()) {
                if (updateModel(
                        it!!,
                        appData.appDataBaseDao().getFavoriteMenu()
                    ).isNotEmpty()
                ) {
                    setupRecyclerView(updateModel(
                        it,
                        appData.appDataBaseDao().getFavoriteMenu()
                    ))
                }else{
                    binding.rcView.visibility = View.GONE
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.tvEmpty.text = getString(R.string.favorite_menu_is_empty)
                }

            } else {
                binding.rcView.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
                binding.tvEmpty.text = getString(R.string.favorite_menu_is_empty)
            }
        })

    }

    //setup favorite menu.
    private fun setupRecyclerView(it: List<ServiceResponse>?) {
        binding.rcView.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        favoriteMenuRcAdapter =
            FavoriteMenuRcAdapter(
                updateModel(
                    it!!,
                    appData.appDataBaseDao().getFavoriteMenu()
                ), this
            )
        binding.rcView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    this@FavoritesMenuActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = favoriteMenuRcAdapter
        }
        favoriteMenuRcAdapter.setOnClickPostsMenu(object : FavoriteMenuRcAdapter.OnClickPostsMenu {
            override fun onClickPostsMenu(favMenu: ServiceResponse, position: Int) {
                startActivity(
                    Intent(
                        this@FavoritesMenuActivity,
                        PostsMenuDetailActivity::class.java
                    ).putExtra("id", favMenu.id)
                )
                pageTransition()
            }

            override fun onClickFavoriteMenu(favMenu: ServiceResponse, position: Int) {
                val t = appData.appDataBaseDao().getFavoriteMenu()
                    .filter { p -> (p.id == favMenu.id) && (favMenu.favoriteStatus == true) }
                if (t.isEmpty()) {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            favMenu.id!!,
                            favMenu.title!!.rendered!!,
                            true
                        )
                    )
                    favoriteMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                } else {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            favMenu.id!!,
                            favMenu.title!!.rendered!!,
                            false
                        )
                    )
                    favoriteMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                }
            }

        })
    }

    //update model for favorite.
    private fun updateModel(
        serviceResponse: List<ServiceResponse>,
        favoriteMenu: List<Favorite>
    ): List<ServiceResponse> {
        favoriteMenu.forEach { f ->
            serviceResponse.find { it.id == f.id }?.favoriteStatus = f.status
        }
        return serviceResponse.filter { s -> s.favoriteStatus }
    }

}