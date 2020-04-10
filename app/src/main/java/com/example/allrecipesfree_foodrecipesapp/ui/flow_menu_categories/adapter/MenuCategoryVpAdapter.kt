package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemMenuCategoryBinding
import com.example.core.data.ServiceResponse

class MenuCategoryVpAdapter(private val countryList: List<ServiceResponse>, private val context: Context) :
    RecyclerView.Adapter<MenuCategoryVpViewHolder>() {

    private var listener: OnClickMenuCategory? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryVpViewHolder {
        return MenuCategoryVpViewHolder(
            ItemMenuCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: MenuCategoryVpViewHolder, position: Int) {

        val  country : ServiceResponse = countryList[holder.adapterPosition]

        Glide.with(context).load(country.imageCategory?.guid).into(holder.binding.imgCate)

        holder.binding.imgCate.apply {
            clipToOutline = true
        }

        holder.binding.tvCountryName.apply {
            text = country.name
        }

        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickMenuCategory(country, position)
            }
        }
    }

    interface OnClickMenuCategory{
        fun onClickMenuCategory(country: ServiceResponse, position: Int)
    }

    fun setOnClickCountry(onClickMenuCategory: OnClickMenuCategory?){
        this.listener = onClickMenuCategory
    }

}

class MenuCategoryVpViewHolder(val binding: ItemMenuCategoryBinding) : RecyclerView.ViewHolder(binding.root)