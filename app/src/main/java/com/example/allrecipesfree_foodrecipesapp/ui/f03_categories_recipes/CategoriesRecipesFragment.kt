package com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentCategoriesRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.CountryRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f03_categories_recipes.adapter.TypeMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.SearchItemsCallBack
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.core.data.CountryCategory
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesRecipesFragment : BaseFragment<FragmentCategoriesRecipesBinding>(),
    SearchItemsCallBack {

    private val viewModel: CategoriesRecipesViewModel by viewModel()
    private lateinit var countryRcAdapter: CountryRcAdapter
    private lateinit var recipesRcAdapter: TypeMenuRcAdapter

    override var layoutResource: Int = R.layout.fragment_categories_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun subscribeLiveData() {

        viewModel.getCountryCategory(requireContext().isInternetConnected())
        viewModel.allCountryCategoryData.observe(viewLifecycleOwner, Observer {
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
            binding.layoutCountryCate.visibility = if(it.isNotEmpty()) View.VISIBLE else View.GONE

            GravitySnapHelper(Gravity.CENTER).attachToRecyclerView(binding.rcCountry)

            countryRcAdapter.setOnClickCountry(object : CountryRcAdapter.OnClickCountry {
                override fun onClickCountry(country: CountryCategory, position: Int) {
                    viewModel.getMenuCategory(
                        country.countryCateId,
                        requireContext().isInternetConnected()
                    )
                }
            })
        })

        viewModel.menuCategoryData.observe(viewLifecycleOwner, Observer {
            recipesRcAdapter = TypeMenuRcAdapter(it)
            binding.rcRecipe.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(
                    activity,
                    2
                )
                adapter = recipesRcAdapter
            }
            binding.layoutMenuCate.visibility = if(it.isNotEmpty()) View.VISIBLE else View.GONE
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