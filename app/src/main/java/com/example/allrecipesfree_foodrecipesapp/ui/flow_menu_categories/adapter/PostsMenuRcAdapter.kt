package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter

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

class PostsMenuRcAdapter(
    private var postsList: List<ServiceResponse>,
    private val context: Context
) : RecyclerView.Adapter<PostsMenuRcViewHolder>() {

    private var listener: OnClickPostsMenu? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsMenuRcViewHolder {
        return PostsMenuRcViewHolder(
            ItemtPostsMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = postsList.size

    override fun onBindViewHolder(holder: PostsMenuRcViewHolder, position: Int) {
        val postMenu: ServiceResponse = postsList[holder.adapterPosition]

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
        fun onClickPostsMenu(postsMenu: ServiceResponse, position: Int)
        fun onClickFavoriteMenu(postsMenu: ServiceResponse, position: Int)
    }

    fun setOnClickPostsMenu(onClickPostsMenu: OnClickPostsMenu) {
        this.listener = onClickPostsMenu
    }

    fun updateData(lisData: List<ServiceResponse>){
        this.postsList = lisData
        notifyDataSetChanged()
    }

}

class PostsMenuRcViewHolder(val binding: ItemtPostsMenuBinding) :
    RecyclerView.ViewHolder(binding.root)