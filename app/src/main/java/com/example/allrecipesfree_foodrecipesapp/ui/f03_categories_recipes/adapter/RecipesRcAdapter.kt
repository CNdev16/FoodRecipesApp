package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemRecipesCateBinding
import com.example.core.data.ServiceResponse

class RecipesRcAdapter :
    RecyclerView.Adapter<RecipesRcViewHolder>() {

    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesRcViewHolder {
        return RecipesRcViewHolder(
            ItemRecipesCateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: RecipesRcViewHolder, position: Int) {

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

    interface OnClickCountry{
        fun onClickCountry(country: ServiceResponse, position: Int)
    }

    fun setOnClickCountry(onClickCountry: OnClickCountry?){
        this.listener = onClickCountry
    }

}

class RecipesRcViewHolder(val binding: ItemRecipesCateBinding) : RecyclerView.ViewHolder(binding.root)