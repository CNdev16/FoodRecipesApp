package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemMenuRcBinding
import com.example.allrecipesfree_foodrecipesapp.utility.gone
import com.example.core.data.RecipePosts

class AllMenuRcAdapter(private val context: Context, private val data: List<RecipePosts>) :
    RecyclerView.Adapter<AllMenuViewHolder>() {

    private var listener: OnClickMenu? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMenuViewHolder {
        return AllMenuViewHolder(
            ItemMenuRcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AllMenuViewHolder, position: Int) {

        val recipe = data[position]
        Glide.with(context).load(recipe.recipePostImg).fitCenter().placeholder(R.drawable.test_img)
            .into(holder.binding.imgMenu)
        holder.binding.tvTitle.apply {
            text = recipe.recipePostTitle
        }
        holder.binding.tvDesc.apply {
            text = "${recipe.countryCateName} , ${recipe.menuCateName}"
        }

        holder.binding.tvCateName.gone()

        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickMenu(recipe, position, holder.binding.imgMenu)
            }
        }
    }

    interface OnClickMenu{
        fun onClickMenu(recipePosts: RecipePosts, position: Int, imageView: ImageView)
    }

    fun setOnClickMenu(onClickMenu: OnClickMenu?){
        this.listener = onClickMenu
    }

}

class AllMenuViewHolder(val binding: ItemMenuRcBinding) : RecyclerView.ViewHolder(binding.root)