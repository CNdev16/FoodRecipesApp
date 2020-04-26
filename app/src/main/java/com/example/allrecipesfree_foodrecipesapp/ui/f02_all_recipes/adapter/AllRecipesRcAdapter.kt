package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemCountryBinding
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipesBinding

class AllRecipesRcAdapter() :
    RecyclerView.Adapter<AllRecipesViewHolder>() {

//    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRecipesViewHolder {
        return AllRecipesViewHolder(
            ItemRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: AllRecipesViewHolder, position: Int) {

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

class AllRecipesViewHolder(val binding: ItemRecipesBinding) : RecyclerView.ViewHolder(binding.root)