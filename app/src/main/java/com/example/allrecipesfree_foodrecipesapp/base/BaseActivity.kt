package com.example.allrecipesfree_foodrecipesapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.utility.hideKeyboard
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity <VB: ViewDataBinding>: AppCompatActivity(){

    abstract var layoutResource: Int
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResource)

    }

    fun isInternetConnected(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun pageTransition() = overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideKeyboard()
        overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }
}