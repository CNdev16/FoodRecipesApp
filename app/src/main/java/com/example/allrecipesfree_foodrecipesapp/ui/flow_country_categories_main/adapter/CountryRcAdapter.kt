package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemCountryBinding

class CountryRcAdapter(private val countryList: List<ServiceResponse>, private val context: Context) :
    RecyclerView.Adapter<CountryRcViewHolder>() {

    private var listener: OnClickCountry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRcViewHolder {
        return CountryRcViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryRcViewHolder, position: Int) {

        val  country : ServiceResponse = countryList[holder.adapterPosition]

        Glide.with(context).load(country.imageCategory?.guid).into(holder.binding.imgCate)

        holder.binding.tvCountryName.apply {
            text = country.name
        }

        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickCountry(country, position)
            }
        }
    }

    interface OnClickCountry{
        fun onClickCountry(country: ServiceResponse, position: Int)
    }

    fun setOnClickCountry(onClickCountry: OnClickCountry?){
        this.listener = onClickCountry
    }

}

class CountryRcViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)