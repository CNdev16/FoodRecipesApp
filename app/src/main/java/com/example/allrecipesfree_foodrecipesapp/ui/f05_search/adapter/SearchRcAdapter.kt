package com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemSearchResultBinding
import com.example.core.data.MenuCategory
import com.example.core.data.RecipePosts

class SearchRcAdapter(private val context: Context, private val data: List<RecipePosts>) :
    RecyclerView.Adapter<SearchRcViewHolder>() {

    private var listener: OnClickResult? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRcViewHolder {
        return SearchRcViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SearchRcViewHolder, position: Int) {

        val recipe = data[position]

        Glide.with(context).load(recipe.recipePostImg).fitCenter().placeholder(R.drawable.img_404)
            .into(holder.binding.imgRecipe)
        holder.binding.tvTitle.text = recipe.recipePostTitle
        holder.binding.layoutRootSearchResult.setOnClickListener {
            listener?.onClickResult(recipe, holder.adapterPosition)
        }
    }

    interface OnClickResult {
        fun onClickResult(recipePosts: RecipePosts, position: Int)
    }

    fun setOnClickResult(onClickResult: OnClickResult?) {
        this.listener = onClickResult
    }

}

class SearchRcViewHolder(val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root)