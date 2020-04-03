package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
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
                    val tf = Typeface.createFromAsset(assetManager, "fonts/Sarabun-Regular.ttf")
                    tab.typeface = tf
                }
            }
        }
    }
}