package com.example.allrecipesfree_foodrecipesapp.utility.epoxy

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import kotlinx.android.synthetic.main.item_recipes_epoxy.view.*

@ModelView(defaultLayout = R.layout.item_recipes_epoxy, fullSpan = false)
class ItemsRecipesEpoxy @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    @ModelProp
    fun setImage(imageUrl: String?){
        Glide.with(context)
            .load(imageUrl)
            .fitCenter()
            .placeholder(R.drawable.img_404)
            .into(imageItems)
    }

    @CallbackProp
    fun onClickItemRecipes(listener: OnClickListener?){
        setOnClickListener(listener)
    }
}