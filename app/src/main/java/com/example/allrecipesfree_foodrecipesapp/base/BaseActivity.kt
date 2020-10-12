package com.example.allrecipesfree_foodrecipesapp.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.hideKeyboard
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    abstract var layoutResource: Int
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResource)

        if (isInternetConnected()) {
            loading()
            subscribeLiveData()
            handleError()
        } else {
            DialogUtils.showDialogOneButton(
                this,
                getString(R.string.dialog_title),
                getString(R.string.no_internet),
                getString(R.string.dialog_btn),
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                        finishAffinity()
                    }
                })
        }
    }

    open fun subscribeLiveData() {
        logD("subscribeLiveData from activity")
    }

    open fun loading() {}

    open fun handleError() {}

    fun pageTransition() =
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideKeyboard()
        //overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }
}