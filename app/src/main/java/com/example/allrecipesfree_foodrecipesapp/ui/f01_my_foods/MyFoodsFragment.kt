package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentMyFoodsBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter.Recipes4uVpAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.CollapStatus
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.PageTransformer
import com.example.allrecipesfree_foodrecipesapp.utility.epoxy.controller.ItemsController
import com.example.allrecipesfree_foodrecipesapp.utility.handleCollap
import com.example.core.data.ResultResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFoodsFragment : BaseFragment<FragmentMyFoodsBinding>() {

    private val viewModel: MyFoodsViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_my_foods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        subscribeLiveData()
    }

    private fun subscribeLiveData() {

//        val vpRecipesRcAdapter = Recipes4uVpAdapter(requireContext(), resultResponse)
//        val itemAdapter = ItemsController().apply {
//            itemsRecipe = resultResponse
//        }
//
//        binding.rcViewRoot.apply {
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//            setHasFixedSize(false)
//            adapter = itemAdapter.adapter
//        }
//        binding.vpRecipe4u.apply {
//            clipToPadding = false
//            clipChildren = false
//            offscreenPageLimit = 3
//
//            adapter = vpRecipesRcAdapter
//            binding.pageIndicatorView.count = vpRecipesRcAdapter.itemCount
//            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    binding.pageIndicatorView.onPageSelected(position)
//                }
//
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                    binding.pageIndicatorView.onPageScrolled(
//                        position,
//                        positionOffset,
//                        positionOffsetPixels
//                    )
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {
//                    binding.pageIndicatorView.onPageScrollStateChanged(state)
//                }
//            })
//        }
    }
}