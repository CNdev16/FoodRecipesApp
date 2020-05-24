package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySplashScreenBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.data.CountryCategory
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    private val viewModel: SplashScreenViewModel by viewModel()

    override var layoutResource: Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun subscribeLiveData() {

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            pageTransition()
            finishAffinity()
        }, 3000L)
    }
}