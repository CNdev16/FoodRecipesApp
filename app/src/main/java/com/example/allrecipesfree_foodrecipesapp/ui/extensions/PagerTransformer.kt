package com.example.allrecipesfree_foodrecipesapp.ui.extensions

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class PagerTransformer(private val nexItemVisible: Float, private val currentItemMargin: Float): ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageTranslationX = nexItemVisible + currentItemMargin
        page.translationX = -pageTranslationX * position
        page.scaleY = 1 - (0.25f * abs(position))
    }
}