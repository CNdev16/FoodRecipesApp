package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentAllRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter.AllRecipesRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.SearchRecipesFragment
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter.SearchRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRecipesFragment : BaseFragment<FragmentAllRecipesBinding>(), SearchItemsCallBack,
    ClearTextCallBack {

    private val viewModel: AllRecipesViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_all_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        MainActivity.apply {
            setOnClickSearchItem(this@AllRecipesFragment)
            setOnClickClearText(this@AllRecipesFragment)
        }
    }

    override fun subscribeLiveData() {
        viewModel.getAllPostsMenuOnlyData(requireContext().isInternetConnected())
        viewModel.allPostsMenuOnlyData.observe(viewLifecycleOwner, Observer {
            val allRecipesRcAdapter = AllRecipesRcAdapter()
            binding.rcViewAllData.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                adapter = allRecipesRcAdapter
            }
        })
    }

    override fun searchItemsCallBack(s: String?) {
        val adapterSearch = SearchRcAdapter()

        binding.rcViewSearchAllData.apply {
            visibility = View.VISIBLE
            slideFromTop()
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }
    }

    override fun clearTextCallBack() {
        if (binding.rcViewSearchAllData.isVisible) {
            binding.rcViewSearchAllData.apply {
                visibility = View.GONE
                slideFromBottom()
            }
        }
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