package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipes4uBinding
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipesBinding

class Recipes4uVpAdapter() :
    RecyclerView.Adapter<Recipes4uViewHolder>() {

//    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recipes4uViewHolder {
        return Recipes4uViewHolder(
            ItemRecipes4uBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: Recipes4uViewHolder, position: Int) {

//        val  country : ServiceResponse = countryList[holder.adapterPosition]
//
//        Glide.with(context).load(country.imageCategory?.guid).into(holder.binding.imgCate)
//
//        holder.binding.tvCountryName.apply {
//            text = country.name
//        }
//
//        holder.binding.cItem.apply {
//            setOnClickListener {
//                listener?.onClickCountry(country, position)
//            }
//        }
    }

//    interface OnClickCountry{
//        fun onClickCountry(country: ServiceResponse, position: Int)
//    }
//
//    fun setOnClickCountry(onClickCountry: OnClickCountry?){
//        this.listener = onClickCountry
//    }

}

class Recipes4uViewHolder(val binding: ItemRecipes4uBinding) : RecyclerView.ViewHolder(binding.root)