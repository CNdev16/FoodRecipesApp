package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemMenuVpBinding
import com.example.core.data.RecipePosts

class MenuVpAdapter(private val context: Context, private val data: List<RecipePosts>) :
    RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            ItemMenuVpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val  recipes = data[position]
        Glide.with(context).load(recipes).placeholder(R.drawable.test_img).into(holder.binding.imgCate)
    }

}

class MenuViewHolder(val binding: ItemMenuVpBinding) : RecyclerView.ViewHolder(binding.root)