package com.example.allrecipesfree_foodrecipesapp.utility

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils.replace
import android.text.style.TypefaceSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.allrecipesfree_foodrecipesapp.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

fun String.formatDateTime(): String {
    return try {
        this.replace("T", " ")
    } catch (e: Exception) {
        this
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun TabLayout.setFont() {
    val vg = this.getChildAt(0) as ViewGroup
    for (i: Int in 0..vg.childCount) {
        val vgTab = vg.getChildAt(i) as ViewGroup?
        vgTab?.let {
            for (j: Int in 0..vgTab.childCount) {
                val tab = vgTab.getChildAt(j)
                if (tab is TextView) {
                    val assetManager: AssetManager = context.assets
                    val tf = Typeface.createFromAsset(assetManager, "fonts/Mitr-Regular.ttf")
                    tab.typeface = tf
                }
            }
        }
    }
}

inline fun FragmentManager.doTransaction(
    func: FragmentTransaction.() ->
    FragmentTransaction
) {
    beginTransaction()
        /*.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit)*/
        .func().commit()
}

fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment) {
    supportFragmentManager.doTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
    supportFragmentManager.doTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.doTransaction { remove(fragment) }
}

fun View.fadeIn(durationMillis: Long = 250) {
    this.startAnimation(AlphaAnimation(0F, 1F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

fun View.fadeOut(durationMillis: Long = 250) {
    this.startAnimation(AlphaAnimation(1F, 0F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

fun View.slideIn() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.activity_open_enter
        ) as Animation
    )
}

fun View.slideOut() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.activity_open_exit
        ) as Animation
    )
}

fun View.slideFromTop() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.open_from_top
        ) as Animation
    )
}

fun View.slideFromBottom() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.close_from_bottom
        ) as Animation
    )
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

enum class CollapStatus(var status: Boolean) {
    ENABLE(true), DISABLE(false)
}

fun handleCollap(
    collap: CollapsingToolbarLayout,
    appBar: AppBarLayout,
    collabStatus: CollapStatus
) {

    val toolbarLayoutParams =
        collap.layoutParams as AppBarLayout.LayoutParams

    val appBarLayoutParams =
        appBar.layoutParams as CoordinatorLayout.LayoutParams

    when (collabStatus) {
        CollapStatus.ENABLE -> {

            toolbarLayoutParams.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            collap.layoutParams = toolbarLayoutParams

            appBarLayoutParams.behavior = AppBarLayout.Behavior()
            appBar.layoutParams = appBarLayoutParams
        }
        CollapStatus.DISABLE -> {
            toolbarLayoutParams.scrollFlags = 0
            collap.layoutParams = toolbarLayoutParams

            appBarLayoutParams.behavior = null
            appBar.layoutParams = appBarLayoutParams
        }
    }

}

fun logD(logMsg: String) = Log.d("APP_LOG", logMsg)

fun Context.isInternetConnected(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
