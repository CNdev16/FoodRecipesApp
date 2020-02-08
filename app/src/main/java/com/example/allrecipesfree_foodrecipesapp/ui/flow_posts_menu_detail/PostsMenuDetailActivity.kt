package com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.data.Favorite
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityPostsMenuDetailBinding
import com.example.allrecipesfree_foodrecipesapp.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.Utils
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsMenuDetailActivity : BaseActivity<ActivityPostsMenuDetailBinding>() {

    private val viewModel: PostsMenuDetailViewModel by viewModel()
    override var layoutResource: Int = R.layout.activity_posts_menu_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postsId = intent.getIntExtra("id", -1)

        setupToolbar()

        subscribeLiveData(postsId)
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun subscribeLiveData(postsId: Int) {
        binding.viewModel = viewModel

        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))
        viewModel.getPostsMenuDetail(postsId)

        viewModel.postsMenuDetail.observe(this, Observer {
            DialogUtils.disMissDialog()

            binding.tvTitlePost.text = it.title!!.rendered

            binding.webViewDetail.apply {
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.domStorageEnabled = true
                setBackgroundColor(0x00000000)

                loadData(
                    "<html><head><style type='text/css'>body{margin-top:auto; margin-bottom:auto;} img{width:100%25;} </style></head><body>${it.content?.rendered}</body></html>",
                    "text/html",
                    "utf-8"
                )
            }
        })
    }
}