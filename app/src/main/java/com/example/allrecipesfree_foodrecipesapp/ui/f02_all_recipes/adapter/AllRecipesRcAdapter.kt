package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipesBinding
import com.example.core.data.RecipePosts

class AllRecipesRcAdapter(private val context: Context, private val data: List<RecipePosts>) :
    RecyclerView.Adapter<AllRecipesViewHolder>() {

    private var listener: OnClickRecipePost? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRecipesViewHolder {
        return AllRecipesViewHolder(
            ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AllRecipesViewHolder, position: Int) {

        val recipe = data[position]
        Glide.with(context).load(recipe.recipePostImg).fitCenter().placeholder(R.drawable.img_404)
            .into(holder.binding.imgCate)
        holder.binding.tvRecipesTitle.apply {
            text = recipe.recipePostTitle
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.binding.imgCate.transitionName = "element $position";
        }

        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickRecipePost(recipe, position, holder.binding.imgCate)
            }
        }
    }

    interface OnClickRecipePost{
        fun onClickRecipePost(recipePosts: RecipePosts, position: Int, imageView: ImageView)
    }

    fun setOnClickRecipePost(onClickRecipePost: OnClickRecipePost?){
        this.listener = onClickRecipePost
    }

}

class AllRecipesViewHolder(val binding: ItemRecipesBinding) : RecyclerView.ViewHolder(binding.root)