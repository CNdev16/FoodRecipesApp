package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemtPostsMenuBinding
import com.example.allrecipesfree_foodrecipesapp.utility.formatDateTime

class FavoriteMenuRcAdapter(
    private var favList: List<ServiceResponse>,
    private val context: Context
) : RecyclerView.Adapter<FavoriteMenuRcViewHolder>() {

    private var listener: OnClickPostsMenu? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMenuRcViewHolder {
        return FavoriteMenuRcViewHolder(
            ItemtPostsMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavoriteMenuRcViewHolder, position: Int) {
        val postMenu: ServiceResponse = favList[holder.adapterPosition]

        holder.binding.tvPostsName.apply {
            text = postMenu.title?.rendered
        }
        holder.binding.tvDate.text = postMenu.date!!.formatDateTime()

        holder.binding.imgFav.apply {
            when (postMenu.favoriteStatus) {
                true -> {setColorFilter(ContextCompat.getColor(context, R.color.colorRed))}
                false -> {setColorFilter(ContextCompat.getColor(context, R.color.colorGreen))}
            }

            setOnClickListener {
                listener?.onClickFavoriteMenu(postMenu, holder.adapterPosition)
            }
        }

        Glide.with(context)
            .load(postMenu.betterFeaturedImage?.sourceUrl)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.img_404))
            .into(holder.binding.imgMenu)

        holder.binding.cItem.apply {
            setOnClickListener {
                listener?.onClickPostsMenu(postMenu, position)
            }
        }
    }

    interface OnClickPostsMenu {
        fun onClickPostsMenu(favMenu: ServiceResponse, position: Int)
        fun onClickFavoriteMenu(favMenu: ServiceResponse, position: Int)
    }

    fun setOnClickPostsMenu(onClickPostsMenu: OnClickPostsMenu) {
        this.listener = onClickPostsMenu
    }

    fun updateData(lisData: List<ServiceResponse>){
        this.favList = lisData
        notifyDataSetChanged()
    }

}

class FavoriteMenuRcViewHolder(val binding: ItemtPostsMenuBinding) :
    RecyclerView.ViewHolder(binding.root)