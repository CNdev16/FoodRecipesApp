package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySplashScreenBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override var layoutResource: Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvVersion.text = "${getString(R.string.version)} ${BuildConfig.VERSION_NAME}"

        if (isInternetConnected()) {

            Handler().postDelayed({
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                pageTransition()
                finishAffinity()
            }, 1000L)

        } else {
            DialogUtils.showDialogOneButton(
                this,
                getString(R.string.dialog_title),
                getString(R.string.dialog_msg),
                getString(R.string.dialog_btn),
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                        finishAffinity()
                    }
                })
        }
    }
}