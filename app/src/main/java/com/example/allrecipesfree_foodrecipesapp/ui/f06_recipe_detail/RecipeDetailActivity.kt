package com.example.allrecipesfree_foodrecipesapp.ui.f06_recipe_detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityRecipeDetailBinding
import com.example.allrecipesfree_foodrecipesapp.utility.CustomActionbar
import com.example.core.data.RecipePosts
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeDetailActivity : BaseActivity<ActivityRecipeDetailBinding>(),
    CustomActionbar.OnClickItemsToolBar {

    private val viewModel: RecipeDetailViewModel by viewModel()
    private var data: RecipePosts? = null
    private lateinit var customActionbar: CustomActionbar

    override var layoutResource: Int = R.layout.activity_recipe_detail

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
        customActionbar.apply {
            setTextHeader(data!!.recipePostName)
            showBackIcon(true)
            showSearchIcon(false)
            search(false)
            setOnClickItemsToolBar(this@RecipeDetailActivity)
        }
    }

    override fun subscribeLiveData() {
        data = intent?.getParcelableExtra("data")
        setupToolbar()

        Glide.with(this)
            .load(data?.recipePostImg)
            .placeholder(R.drawable.test_img)
            .into(binding.imgRecipe)

        binding.webViewDetail.apply {
            setBackgroundColor(0x00000000)
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            settings.setAppCacheEnabled(true)
            settings.cacheMode = LOAD_CACHE_ELSE_NETWORK
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            //settings.useWideViewPort = true
            settings.domStorageEnabled = true

            val text =
                ("<html><style type='text/css'>@font-face { font-family: roboto; src: url('fonts/Roboto-Regular.ttf'); } body p {font-family: roboto;}</style>"
                        + "<body >" + "<p align=\"justify\" style=\"font-size: 22px; font-family: roboto;\">" + data?.recipeContent + "</p> " + "</body></html>")

            loadDataWithBaseURL("file:///android_asset/", text, "text/html", "utf-8", null)
        }


        binding.youtubeView.apply {
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo("cEs2gVzRSGE", 0F)
                }
            })
        }
        lifecycle.addObserver(binding.youtubeView)
    }

    override fun onClickItemRight(text: String?) {
    }

    override fun onClickItemLeft() {
        this.finishAfterTransition()
    }

    override fun onClickClearText() {
    }
}