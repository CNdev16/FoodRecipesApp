package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.BuildConfig
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySplashScreenBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    private val viewModel: SplashScreenViewModel by viewModel()

    override var layoutResource: Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        if (isInternetConnected()) {
            subscribeLiveData()
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

    private fun subscribeLiveData() {

        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))

        viewModel.getAllRecipesData()
        viewModel.allRecipesData.observe(this, Observer {
            //Return List<ResultResponse>!
            DialogUtils.disMissDialog()

            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            pageTransition()
            finishAffinity()
        })
    }
}