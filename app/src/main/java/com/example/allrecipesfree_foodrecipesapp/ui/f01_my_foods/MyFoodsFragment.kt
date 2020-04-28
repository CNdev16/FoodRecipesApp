package com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods

import android.os.Bundle
import android.view.View
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentMyFoodsBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f01_my_foods.adapter.Recipes4uVpAdapter
import com.example.allrecipesfree_foodrecipesapp.utility.PageTransformer
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
        binding.vpRecipe4u.apply {
            adapter = vpRecipesRcAdapter
//            setPageTransformer(
//                PageTransformer(
//                    resources.getDimensionPixelOffset(R.dimen.pageMargin),
//                    resources.getDimensionPixelSize(R.dimen.offset)
//                )
//            )
        }

    }
}