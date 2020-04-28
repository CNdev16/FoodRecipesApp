package com.example.allrecipesfree_foodrecipesapp.utility.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginLeft
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.allrecipesfree_foodrecipesapp.R
import kotlinx.android.synthetic.main.label_header_epoxy.view.*

@ModelView(defaultLayout = R.layout.label_header_epoxy, fullSpan = false)
class HeaderLabelEpoxy @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context, attrs, defStyleAttr
) {


    @ModelProp
    fun setHeader(str: CharSequence?) {
        tvHeaderLabel.text = str
    }

}