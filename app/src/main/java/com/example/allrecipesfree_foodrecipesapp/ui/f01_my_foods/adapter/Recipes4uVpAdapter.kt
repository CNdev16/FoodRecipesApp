package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipes4uBinding
import com.example.core.data.ResultResponse
import com.example.core.data.ServiceResponse

class Recipes4uVpAdapter(private val context: Context, private val data: List<ResultResponse>) :
    RecyclerView.Adapter<Recipes4uViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recipes4uViewHolder {
        return Recipes4uViewHolder(
            ItemRecipes4uBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data[3].subCateList!![3].postsList.size

    override fun onBindViewHolder(holder: Recipes4uViewHolder, position: Int) {
        val  recipes = data[3].subCateList!![3].postsList[position].postImg
        Glide.with(context).load(recipes).placeholder(R.drawable.img_404).into(holder.binding.imgCate)
    }

}

class Recipes4uViewHolder(val binding: ItemRecipes4uBinding) : RecyclerView.ViewHolder(binding.root)