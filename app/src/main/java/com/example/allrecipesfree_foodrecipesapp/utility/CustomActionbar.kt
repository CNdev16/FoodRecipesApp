package com.example.allrecipesfree_foodrecipesapp.utility

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
import com.example.allrecipesfree_foodrecipesapp.databinding.LayoutCustomToolbarBinding

class CustomActionbar @JvmOverloads constructor(
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
        setTypeFaceHeader(StyleFonts.MEDIUM)
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
        REGULAR, MEDIUM, BOLD
    }

    fun setTypeFaceHeader(style: StyleFonts): TextView {
        val tv = binding.tvHeader
        return tv.apply {
            var tf: Typeface? = when (style) {
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
            str = ""
            if (getTextSearch().isEmpty()) {
                closeSearchBox()
            } else {

            }
        }
    }

    fun openSearchBox() {
        if (getTextSearch().isNotEmpty()){
            listener?.onClickItemRight(binding.edtSearch.text.toString())
        }else{
            binding.edtSearch.apply {
                visibility = View.VISIBLE
                setText("")
                slideIn()
            }
            binding.tvHeader.slideOut()
            isSearchClicked = false
        }
    }

    fun closeSearchBox() {
        binding.edtSearch.apply {
            visibility = View.GONE
            setText("")
            //slideOut()
        }
        binding.tvHeader.slideIn()
        isSearchClicked = true
    }

    fun getTextSearch(): String {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                str = s.toString()
                isSearchClicked = str.isNotEmpty()
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