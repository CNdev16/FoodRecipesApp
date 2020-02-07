package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityFavoritesMenuBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter.PostsMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesMenuActivity : BaseActivity<ActivityFavoritesMenuBinding>(){

    private val viewModel: FavoritesMenuViewModel by viewModel()
    private lateinit var postsMenuRcAdapter: PostsMenuRcAdapter

    override var layoutResource: Int = R.layout.activity_favorites_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        setupSubscribeLiveData()
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

    private fun setupSubscribeLiveData() {
        binding.viewModel = viewModel

        viewModel.fetchAllPostsMenu()

        viewModel.allPostsMenu.observe(this, Observer {
            if (it.isNotEmpty()) {
                setupRecyclerView(it)
            } else {
                binding.rcView.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
                binding.tvEmpty.text = "Menu is empty."
            }
        })

    }

    private fun setupRecyclerView(it: List<ServiceResponse>?) {
        binding.rcView.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        postsMenuRcAdapter =
            PostsMenuRcAdapter(
                it!!, this
            )
        binding.rcView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    this@FavoritesMenuActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = postsMenuRcAdapter
        }
        postsMenuRcAdapter.setOnClickPostsMenu(object : PostsMenuRcAdapter.OnClickPostsMenu {
            override fun onClickPostsMenu(postsMenu: ServiceResponse, position: Int) {
                startActivity(
                    Intent(
                        this@FavoritesMenuActivity,
                        PostsMenuDetailActivity::class.java
                    ).putExtra("id", postsMenu.id)
                )
                pageTransition()
            }

            override fun onClickFavoriteMenu(postsMenu: ServiceResponse, position: Int) {
                Log.d("chok", "click favorite.")
            }

        })
    }

}