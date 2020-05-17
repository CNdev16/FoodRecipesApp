package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentMyFoodsBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter.Recipes4uVpAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.epoxy.controller.ItemsController
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFoodsFragment : BaseFragment<FragmentMyFoodsBinding>() {

    private val viewModel: MyFoodsViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_my_foods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun subscribeLiveData() {

        viewModel.getAllDataFromDb(requireContext().isInternetConnected())
        viewModel.allDataFromDb.observe(viewLifecycleOwner, Observer {
            Log.d("printtt==>", Gson().toJson(it))

            val vpRecipesRcAdapter = Recipes4uVpAdapter(requireContext(), it)
            val itemAdapter = ItemsController().apply {
                itemsRecipe = it
            }

            binding.rcViewRoot.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(false)
                adapter = itemAdapter.adapter
            }
            binding.vpRecipe4u.apply {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3

                adapter = vpRecipesRcAdapter
                binding.pageIndicatorView.count = vpRecipesRcAdapter.itemCount
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        binding.pageIndicatorView.onPageSelected(position)
                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        binding.pageIndicatorView.onPageScrolled(
                            position,
                            positionOffset,
                            positionOffsetPixels
                        )
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        binding.pageIndicatorView.onPageScrollStateChanged(state)
                    }
                })
            }
        })
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