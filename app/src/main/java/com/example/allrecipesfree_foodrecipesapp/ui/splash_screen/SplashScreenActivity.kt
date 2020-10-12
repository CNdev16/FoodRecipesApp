package com.example.allrecipesfree_foodrecipesapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivitySplashScreenBinding
import com.example.allrecipesfree_foodrecipesapp.ui.main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.allrecipesfree_foodrecipesapp.utility.logD
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

        viewModel.getAllDataFromService(isInternetConnected())

        viewModel.allDataFromService.observe(this, Observer {
            logD("Get Data Successfully... ${Gson().toJson(it)}")

            it.forEach { i ->
                viewModel.insertCountryData(i, isInternetConnected())
            }

            it.forEach { i ->
                i.menuCategoryList!!.forEach { j ->
                    viewModel.insertMenuData(j, isInternetConnected())
                }
            }

            it.forEach { i ->
                i.menuCategoryList!!.forEach { j ->
                    j.recipePostsList!!.forEach { k ->
                        viewModel.insertRecipeData(k, isInternetConnected())
                    }
                }
            }

            viewModel.insertCountryToLocal.observe(this, Observer {
                logD("Insert Country Data Successfully...")
            })

            viewModel.insertMenuToLocal.observe(this, Observer {
                logD("Insert Menu Data Successfully...")
            })

            viewModel.insertRecipeToLocal.observe(this, Observer {
                logD("Insert Recipe Data Successfully...")
            })

            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            pageTransition()
            finishAffinity()
        })
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
            DialogUtils.showDialogOneButton(
                this,
                it[0],
                it[1],
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