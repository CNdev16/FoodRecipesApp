package com.example.allrecipesfree_foodrecipesapp.utility

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.databinding.LayoutCustomToolbarBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.CategoriesRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.FavoriteRecipesFragment

class CustomActionbar @JvmOverloads constructor(
    private val activity: AppCompatActivity,
    private val context: Context,
    supportActionBar: ActionBar?
) {

    private lateinit var binding: LayoutCustomToolbarBinding
    private var actionBar: ActionBar? = null
    private var isAnimated = true
    private var isSearchClicked = true
    private var str = ""
    private var listener: OnClickItemsToolBar? = null

    init {
        setupCustomToolbar(context, supportActionBar)
    }

    private fun setupCustomToolbar(context: Context, supportActionBar: ActionBar?) {
        binding = LayoutCustomToolbarBinding.inflate(LayoutInflater.from(context))
        setTypeFaceHeader(StyleFonts.LIGHT)
        binding.imgRight.setOnClickListener {
            search(isSearchClicked)
        }
        binding.imgLeft.setOnClickListener { listener?.onClickItemLeft() }
        actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayShowCustomEnabled(true)
            it.customView = binding.root
        }
    }

    fun setTextHeader(@StringRes id: Int) {
        val s = context.resources.getString(id)
        setTextHeader(s)
    }

    fun setTextHeader(str: String) {
        binding.tvHeader.text = str
        binding.tvHeader.slideIn()
    }

    fun showSearchIcon(isShowIconSearch: Boolean) {
        if (isShowIconSearch) {
            binding.imgRight.visibility =
                View.VISIBLE
            binding.imgRight.isEnabled = true

            if (isAnimated) {
                binding.imgRight.fadeIn()
                isAnimated = false
            }
        } else {
            binding.imgRight.visibility = View.INVISIBLE
            binding.imgRight.isEnabled = false
            binding.imgRight.fadeOut()
            isAnimated = true
        }
    }

    enum class StyleFonts {
        LIGHT, EXTRA_LIGHT, REGULAR, MEDIUM, BOLD
    }

    fun setTypeFaceHeader(style: StyleFonts): TextView {
        val tv = binding.tvHeader
        return tv.apply {
            var tf: Typeface? = when (style) {
                StyleFonts.LIGHT -> Typeface.createFromAsset(context.assets, "fonts/Mitr-Light.ttf")
                StyleFonts.EXTRA_LIGHT -> Typeface.createFromAsset(
                    context.assets,
                    "fonts/Mitr-ExtraLight.ttf"
                )
                StyleFonts.REGULAR -> Typeface.createFromAsset(
                    context.assets,
                    "fonts/Mitr-Regular.ttf"
                )
                StyleFonts.MEDIUM -> Typeface.createFromAsset(
                    context.assets,
                    "fonts/Mitr-Medium.ttf"
                )
                StyleFonts.BOLD -> Typeface.createFromAsset(context.assets, "fonts/Mitr-Bold.ttf")
            }
            tf?.let {
                tv.typeface = it
            }
        }
    }

    fun search(state: Boolean) {
        if (state) {
            openSearchBox()
        } else {
            closeSearchBox()
        }
    }

    fun openSearchBox() {
        if (getTextSearch().isNotEmpty()) {
            binding.imgRight.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_search_black_24dp
                )
            )
            listener?.onClickItemRight(binding.edtSearch.text.toString())
        } else {
            binding.imgRight.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_clear_black_24dp
                )
            )
            binding.edtSearch.apply {
                visibility = View.VISIBLE
                setText("")
                requestFocus()
                setSelection(this.text.length)
                //slideIn()
            }
            binding.tvHeader.apply {
                visibility = View.INVISIBLE
                slideOut()
            }
            isSearchClicked = false
        }

        val currentFragment: Fragment? =
            activity.supportFragmentManager.findFragmentById(R.id.contentContainer)
        currentFragment?.let {
            when (it) {
                is AllRecipesFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_room_service_black_24dp),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                    }
                }
                is CategoriesRecipesFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_restaurant_black_24dp),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                    }
                }
                is FavoriteRecipesFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_favorite_black_24dp),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                    }
                }
                else -> {
                    throw Exception("Error cast fragment.")
                }
            }
        }

    }

    fun closeSearchBox() {
        binding.edtSearch.apply {
            visibility = View.GONE
            setText("")
            //slideOut()
        }
        binding.tvHeader.apply {
            visibility = View.VISIBLE
            slideIn()
        }
        isSearchClicked = true
        binding.imgRight.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_search_black_24dp
            )
        )
    }

    fun getTextSearch(): String {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                str = s.toString()
                isSearchClicked = str.isNotEmpty()
                if (str.isNotEmpty()) {
                    binding.imgRight.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_search_black_24dp
                        )
                    )
                } else {
                    binding.imgRight.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_clear_black_24dp
                        )
                    )
                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

        })

        return str
    }

    fun setOnClickItemsToolBar(onClickItemsToolBar: OnClickItemsToolBar) {
        this.listener = onClickItemsToolBar
    }

    interface OnClickItemsToolBar {
        fun onClickItemRight(text: String?)
        fun onClickItemLeft()
    }
}