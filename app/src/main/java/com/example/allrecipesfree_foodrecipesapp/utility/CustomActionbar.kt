package com.example.allrecipesfree_foodrecipesapp.utility

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.databinding.CustomToolbarBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.AllMenuFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.CategoriesRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.FavoriteRecipesFragment

class CustomActionbar @JvmOverloads constructor(
    private val activity: AppCompatActivity,
    private val context: Context,
    supportActionBar: ActionBar?
) {

    private lateinit var binding: CustomToolbarBinding
    private var actionBar: ActionBar? = null
    private var isAnimated = true
    private var isSearchClicked = true
    private var str = ""
    private var listener: OnClickItemsToolBar? = null

    init {
        setupCustomToolbar(context, supportActionBar)
    }

    private fun setupCustomToolbar(context: Context, supportActionBar: ActionBar?) {
        binding = CustomToolbarBinding.inflate(LayoutInflater.from(context))
        setTypeFaceHeader(StyleFonts.MEDIUM)
        binding.imgRight.setOnClickListener {
            search(isSearchClicked)
        }
        binding.imgLeft.setOnClickListener { listener?.onClickItemLeft() }
        binding.imgClearText.setOnClickListener {
            binding.edtSearch.setText("")
            listener?.onClickClearText()
        }
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

    fun setTextHeader(str: String = "") {
        if (str == "") binding.tvHeader.inVisible() else binding.tvHeader.visible()
        binding.tvHeader.text = str
        //binding.tvHeader.slideIn()
    }

    fun showSearchIcon(isShowIconSearch: Boolean) {
        if (isShowIconSearch) {
            binding.layoutInputSearch.visibility =
                View.VISIBLE
            binding.imgRight.visibility = View.VISIBLE
            binding.imgRight.isEnabled = true

            if (isAnimated) {
                //binding.imgRight.fadeIn()
                isAnimated = false
            }
        } else {
            binding.imgRight.visibility = View.INVISIBLE
            binding.imgRight.isEnabled = false
            //binding.imgRight.fadeOut()
            isAnimated = true
        }
    }

    fun showBackIcon(isShow: Boolean = false) {
        if (isShow) {
            binding.imgLeft.apply {
                visibility = View.VISIBLE
                //fadeIn()
            }

        } else {
            binding.imgLeft.apply {
                visibility = View.GONE
                //fadeOut()
            }
        }
    }

    enum class StyleFonts {
        REGULAR, MEDIUM
    }

    fun setTypeFaceHeader(style: StyleFonts): TextView {
        val tv = binding.tvHeader
        return tv.apply {
            var tf: Typeface? = when (style) {
                StyleFonts.REGULAR -> Typeface.createFromAsset(
                    context.assets,
                    "fonts/Roboto-Regular.ttf"
                )
                StyleFonts.MEDIUM -> Typeface.createFromAsset(
                    context.assets,
                    "fonts/Roboto-Medium.ttf"
                )
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
                    R.drawable.round_search_black_18dp
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
            binding.layoutInputSearch.visibility = View.VISIBLE

            binding.edtSearch.apply {
                setText("")
                requestFocus()
                showKeyboard()
                setSelection(this.text!!.length)
            }
            binding.tvHeader.apply {
                visibility = View.INVISIBLE
                //slideOut()
            }
            isSearchClicked = false
        }

        val currentFragment: Fragment? =
            activity.supportFragmentManager.findFragmentById(R.id.contentContainer)
        currentFragment?.let {
            when (it) {
                is AllMenuFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.round_room_service_white_18dp
                            ),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                        hint = "Search... ${AllMenuFragment::class.java.simpleName}"
                    }
                }
                is CategoriesRecipesFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.round_restaurant_white_18dp),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                        hint = "Search... ${CategoriesRecipesFragment::class.java.simpleName}"
                    }
                }
                is FavoriteRecipesFragment -> {
                    binding.edtSearch.apply {
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.round_favorite_black_18dp),
                            null,
                            null,
                            null
                        )
                        compoundDrawablePadding = 4
                        hint = "Search... ${FavoriteRecipesFragment::class.java.simpleName}"
                    }
                }
                else -> {
                    throw Exception("Error cast fragment.")
                }
            }
        }

    }

    fun closeSearchBox() {
        binding.layoutInputSearch.apply {
            visibility = View.GONE
            binding.edtSearch.setText("")
        }
        binding.tvHeader.apply {
            visibility = View.VISIBLE
            //slideIn()
        }
        listener?.onClickClearText()
        isSearchClicked = true
        binding.edtSearch.hideKeyboard()
        binding.imgRight.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.round_search_black_18dp
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
                            R.drawable.round_search_black_18dp
                        )
                    )
                    binding.imgClearText.apply {
                        visibility = View.VISIBLE
                    }

                } else {
                    binding.imgRight.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_clear_black_24dp
                        )
                    )
                    binding.imgClearText.apply {
                        visibility = View.GONE
                    }
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
        fun onClickClearText()
    }
}