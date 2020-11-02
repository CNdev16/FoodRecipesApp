package com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseFragment
import com.example.allrecipesfree_foodrecipesapp.databinding.FragmentAllRecipesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.f02_all_recipes.adapter.AllMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f05_search.adapter.SearchRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.f06_recipe_detail.RecipeDetailActivity
import com.example.allrecipesfree_foodrecipesapp.ui.main.MainActivity
import com.example.allrecipesfree_foodrecipesapp.utility.*
import com.example.core.data.RecipePosts
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllMenuFragment : BaseFragment<FragmentAllRecipesBinding>(), SearchItemsCallBack,
    ClearTextCallBack {

    private val viewModel: AllMenuViewModel by viewModel()
    private var allMenuRcAdapter: AllMenuRcAdapter? = null
    private var recipePostsData: List<RecipePosts>? = null

    override var layoutResource: Int = R.layout.fragment_all_recipes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD("AllRecipesFragment is created.")

        binding.viewModel = viewModel
    }

    override fun subscribeLiveData() {
        viewModel.getRecipePosts(requireContext().isInternetConnected())
        viewModel.recipeData.observe(viewLifecycleOwner, Observer {
            logD("getRecipePosts")
            it?.let {
                recipePostsData = it
                MainActivity.apply {
                    setOnClickSearchItem(this@AllMenuFragment)
                    setOnClickClearText(this@AllMenuFragment)
                }

                allMenuRcAdapter = AllMenuRcAdapter(requireContext(), it)
                binding.rcViewAllData.apply {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    adapter = allMenuRcAdapter
                }

                allMenuRcAdapter?.setOnClickMenu(object : AllMenuRcAdapter.OnClickMenu{
                    override fun onClickMenu(
                        recipePosts: RecipePosts,
                        position: Int,
                        imageView: ImageView
                    ) {
                        val intent = Intent(requireContext(), RecipeDetailActivity::class.java)
                        intent.putExtra("data", recipePosts)
                        startActivity(intent)
                    }
                })
            }
        })
    }

    override fun searchItemsCallBack(s: String?) {
        val recipeFilter = recipePostsData?.filter { it.recipePostTitle.contains(s!!) }
        val adapterSearch = SearchRcAdapter(requireContext(), recipeFilter!!)

        binding.rcViewSearchAllData.apply {
            visibility = View.VISIBLE
            slideFromTop()
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }

        adapterSearch.setOnClickResult(object : SearchRcAdapter.OnClickResult {
            override fun onClickResult(recipePosts: RecipePosts, position: Int) {
                val lym = binding.rcViewAllData.layoutManager as LinearLayoutManager
                lym.scrollToPositionWithOffset(recipeFilter.indexOfFirst { r -> r.recipePostId == recipePosts.recipePostId }, 0)

                binding.rcViewSearchAllData.apply {
                    visibility = View.GONE
                    slideFromBottom()
                }
            }
        })

        hideKeyboard()
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