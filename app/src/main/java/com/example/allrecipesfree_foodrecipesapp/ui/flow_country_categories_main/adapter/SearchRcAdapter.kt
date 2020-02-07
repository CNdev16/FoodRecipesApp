package com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allrecipesfree_foodrecipesapp.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemSearchResultBinding

class SearchRcAdapter(private val listResult: List<ServiceResponse>, private val context: Context) :
    RecyclerView.Adapter<SearchRcViewHolder>() {

    private var listener: OnClickResult? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRcViewHolder {
        return SearchRcViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listResult.size

    override fun onBindViewHolder(holder: SearchRcViewHolder, position: Int) {

        val result: ServiceResponse = listResult[holder.adapterPosition]

        holder.binding.tvTitle.text = result.title!!.rendered

        holder.binding.layoutRootSearchResult.setOnClickListener {
            listener?.onClickResult(result, holder.adapterPosition)
        }
    }

    interface OnClickResult {
        fun onClickResult(result: ServiceResponse, position: Int)
    }

    fun setOnClickResult(onClickResult: OnClickResult?) {
        this.listener = onClickResult
    }

}

class SearchRcViewHolder(val binding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root)