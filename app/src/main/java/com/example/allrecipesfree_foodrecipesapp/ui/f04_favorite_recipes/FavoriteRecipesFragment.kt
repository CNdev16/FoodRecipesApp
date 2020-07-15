package com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentFavoriteRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f04_favorite_recipes.adapter.FavoriteRcAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteRecipesFragment() : BaseFragment<FragmentFavoriteRecipesBinding>(),
    SearchItemsCallBack {

    private val viewModel: FavoriteRecipesViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_favorite_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        subscribeLiveData()
    }

    override fun subscribeLiveData() {

        viewModel.getAllDataFromDb(requireContext().isInternetConnected())
        viewModel.allDataFromDb.observe(viewLifecycleOwner, Observer {
            val favoriteRcAdapter = FavoriteRcAdapter()
            binding.rcViewFav.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                adapter = favoriteRcAdapter
            }
        })

    }

    override fun searchItemsCallBack(s: String?) {
    }

    override fun loading() {
        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if (it) DialogUtils.showProgressDialog(
                requireContext(),
                getString(R.string.progress_msg)
            ) else DialogUtils.disMissDialog()
        })
    }

    override fun handleError() {
        viewModel.handleError.observe(this, Observer {
            DialogUtils.showDialogOneButton(
                requireContext(),
                it[0],
                it[1],
                "Ok",
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                    }
                }
            )
        })
    }
}