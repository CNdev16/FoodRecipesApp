package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.tabs.TabLayout

object Utils {
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }
    }

    fun changeTabFont(context: Context, tab: TabLayout){
        val vg =  tab.getChildAt(0) as ViewGroup
        val tabCount: Int = vg.childCount

        for (j in 0..tabCount){
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildCount:Int = vgTab.childCount
            for (i in 0..tabChildCount){
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView){
                    val assetManager: AssetManager  = context.assets
                    val tf = Typeface.createFromAsset(assetManager, "fonts/Sarabun-Regular.ttf")
                    tabViewChild.typeface = tf
                }
            }
        }

    }
}