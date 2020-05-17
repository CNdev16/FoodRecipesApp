package com.example.allrecipesfree_foodrecipesapp.ui.flow_splash_screen

import android.content.Intent
import android.os.Bundle
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

        viewModel.getAllRecipesData(isInternetConnected())
        viewModel.allRecipesData.observe(this, Observer {
            addDataToDb(it)
        })
    }

    private fun addDataToDb(it: List<CountryCategory>?) {
        it?.let {
            it.forEach { i ->
                viewModel.addAllRecipesData(i, isInternetConnected())
            }

            viewModel.addPostsToDb.observe(this, Observer {
                logD("Insert success ==> $it")
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                pageTransition()
                finishAffinity()
            })
        } ?: throw IllegalArgumentException("null")
    }

    override fun loading() {
        viewModel.showLoading.observe(this, Observer {
            if (it) DialogUtils.showProgressDialog(
                this,
                getString(R.string.progress_msg)
            ) else DialogUtils.disMissDialog()
        })
    }

    override fun handleError() {
        viewModel.handleError.observe(this, Observer {
            logD(it)
            DialogUtils.showDialogOneButton(
                this,
                "Error.",
                it,
                "Ok",
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                    }
                }
            )
        })
    }
}