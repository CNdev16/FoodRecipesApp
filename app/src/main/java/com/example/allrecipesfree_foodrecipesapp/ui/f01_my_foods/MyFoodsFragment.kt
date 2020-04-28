package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentMyFoodsBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter.Recipes4uVpAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.PageTransformer
import com.example.allrecipesfree_foodrecipesapp.utility.epoxy.controller.ItemsController
import com.example.core.data.ResultResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFoodsFragment : BaseFragment<FragmentMyFoodsBinding>() {

    private val viewModel: MyFoodsViewModel by viewModel()

    override var layoutResource: Int = R.layout.fragment_my_foods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        val vpRecipesRcAdapter = Recipes4uVpAdapter()

        with(binding.vpRecipe4u) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }
        with(binding.rcViewRoot) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(false)
        }
        val itemAdapter = ItemsController().apply {
            itemsRecipe = getList()
        }
        binding.rcViewRoot.apply {
            adapter = itemAdapter.adapter
        }
        binding.vpRecipe4u.apply {
            adapter = vpRecipesRcAdapter
//            setPageTransformer(
//                PageTransformer(
//                    resources.getDimensionPixelOffset(R.dimen.pageMargin),
//                    resources.getDimensionPixelSize(R.dimen.offset)
//                )
//            )

            binding.pageIndicatorView.count = 10

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

    }

    private fun getList() = listOf<ResultResponse>(
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", ""),
        ResultResponse(0, "", "")

    )
}