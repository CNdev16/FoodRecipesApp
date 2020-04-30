package com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ItemSearchResultBinding

class SearchRcAdapter() :
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

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: SearchRcViewHolder, position: Int) {

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