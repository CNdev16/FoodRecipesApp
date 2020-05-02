package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText

class CustomEditText @JvmOverloads constructor(
    context: Context? = null,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attr, defStyleAttr) {

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused) this.hideKeyboard()
    }
}