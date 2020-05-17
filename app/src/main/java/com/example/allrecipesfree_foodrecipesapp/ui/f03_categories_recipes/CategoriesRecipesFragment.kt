package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentCategoriesRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.CountryRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.RecipesRcAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.SearchItemsCallBack
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.example.core.data.CountryCategory
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesRecipesFragment : BaseFragment<FragmentCategoriesRecipesBinding>(),
    SearchItemsCallBack {

    private val viewModel: CategoriesRecipesViewModel by viewModel()
    private lateinit var countryRcAdapter: CountryRcAdapter
    private lateinit var recipesRcAdapter: RecipesRcAdapter

    override var layoutResource: Int = R.layout.fragment_categories_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun subscribeLiveData() {

        viewModel.getAllDataFromDb(requireContext().isInternetConnected())
        viewModel.allDataFromDb.observe(viewLifecycleOwner, Observer {
            Log.d("printtt==>", "${Gson().toJson(it)} \n ${it.size}")
            Log.d("printtt==>", "${it.size}")

            countryRcAdapter = CountryRcAdapter(it)
            binding.rcCountry.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                adapter = countryRcAdapter
            }
            countryRcAdapter.setOnClickCountry(object : CountryRcAdapter.OnClickCountry{
                override fun onClickCountry(country: CountryCategory, position: Int) {
                    recipesRcAdapter = RecipesRcAdapter(it[position].menuCategoryList!!)
                    binding.rcRecipe.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(
                            activity,
                            2
                        )
                        adapter = recipesRcAdapter
                    }
                }

            })
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
            logD(it)
            DialogUtils.showDialogOneButton(
                requireContext(),
                "Error.",
                it,
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