package com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemMenuRcBinding
import com.example.allrecipesfree_foodrecipesapp.utility.gone
import com.example.allrecipesfree_foodrecipesapp.utility.visible

class FavoriteRcAdapter() :
    RecyclerView.Adapter<FavoriteViewHolder>() {

//    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMenuRcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        holder.binding.tvCateName.gone()
        holder.binding.fav.visible()

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

class FavoriteViewHolder(val binding: ItemMenuRcBinding) : RecyclerView.ViewHolder(binding.root)