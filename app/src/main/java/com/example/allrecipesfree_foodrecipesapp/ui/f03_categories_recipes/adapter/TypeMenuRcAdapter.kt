package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemMenuRcBinding
import com.example.allrecipesfree_foodrecipesapp.utility.gone
import com.example.core.data.MenuCategory

class TypeMenuRcAdapter(private val data: List<MenuCategory>) :
    RecyclerView.Adapter<TypeMenuRcViewHolder>() {

    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeMenuRcViewHolder {
        return TypeMenuRcViewHolder(
            ItemMenuRcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TypeMenuRcViewHolder, position: Int) {

        holder.binding.tvCateName.gone()

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
        //fun onClickCountry(country: ServiceResponse, position: Int)
    }

    fun setOnClickCountry(onClickCountry: OnClickCountry?){
        //this.listener = onClickCountry
    }

}

class TypeMenuRcViewHolder(val binding: ItemMenuRcBinding) : RecyclerView.ViewHolder(binding.root)