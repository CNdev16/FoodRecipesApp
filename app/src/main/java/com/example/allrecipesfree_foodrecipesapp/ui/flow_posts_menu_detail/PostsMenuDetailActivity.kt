package com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityPostsMenuDetailBinding
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsMenuDetailActivity : BaseActivity<ActivityPostsMenuDetailBinding>() {

    private val viewModel: PostsMenuDetailViewModel by viewModel()
    override var layoutResource: Int = R.layout.activity_posts_menu_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postsId = intent.getIntExtra("id", -1)

        setupToolbar()
        binding.imgMenu.transitionName = "imgMenu"
        Glide.with(this)
            .load("https://wordpress-281738-974608.cloudwaysapps.com/wp-content/uploads/2020/02/Android-Logo-2019-800x722-2.png")
            .into(binding.imgMenu)
        //subscribeLiveData(postsId)
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

//    @SuppressLint("SetJavaScriptEnabled")
//    private fun subscribeLiveData(postsId: Int) {
//        binding.viewModel = viewModel
//
//        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))
//        viewModel.getPostsMenuDetail(postsId)
//
//        viewModel.postsMenuDetail.observe(this, Observer {
//            DialogUtils.disMissDialog()
//
//            binding.tvTitlePost.text = it.title!!.rendered
//
//            binding.webViewDetail.apply {
//                webChromeClient = WebChromeClient()
//                settings.javaScriptEnabled = true
//                settings.loadWithOverviewMode = true
//                settings.useWideViewPort = true
//                settings.domStorageEnabled = true
//                setBackgroundColor(0x00000000)
//
//                loadData(
//                    "<html><head><style type='text/css'>body{margin-top:auto; margin-bottom:auto;} img{width:100%25;} </style></head><body>${it.content?.rendered}</body></html>",
//                    "text/html",
//                    "utf-8"
//                )
//            }
//        })
//    }
}