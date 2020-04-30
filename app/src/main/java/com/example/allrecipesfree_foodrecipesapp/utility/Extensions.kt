package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils.replace
import android.text.style.TypefaceSpan
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.allrecipesfree_foodrecipesapp.R
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

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
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
    beginTransaction().setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit)
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