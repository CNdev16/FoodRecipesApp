package com.example.allrecipesfree_foodrecipesapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySplashScreenBinding
import com.example.allrecipesfree_foodrecipesapp.ui.main.MainActivity
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
            //pageTransition()
            finishAffinity()
        }, 3000L)
    }
}