package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemCountryCateBinding
import com.example.core.data.CountryCategory

class CountryRcAdapter(private val data: List<CountryCategory>) :
    RecyclerView.Adapter<CountryRcViewHolder>() {

    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRcViewHolder {
        return CountryRcViewHolder(
            ItemCountryCateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CountryRcViewHolder, position: Int) {

//        holder.binding.tvCountryName.apply {
//            text = country.name
//        }
//
        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickCountry(data[position], position)
            }
        }
    }

    interface OnClickCountry{
        fun onClickCountry(country: CountryCategory, position: Int)
    }

    fun setOnClickCountry(onClickCountry: OnClickCountry?){
        this.listener = onClickCountry
    }

}

class CountryRcViewHolder(val binding: ItemCountryCateBinding) : RecyclerView.ViewHolder(binding.root)