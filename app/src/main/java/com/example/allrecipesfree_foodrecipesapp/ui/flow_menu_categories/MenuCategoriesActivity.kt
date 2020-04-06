package com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.core.data.Favorite
import com.example.core.data.ServiceResponse
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityMenuCategoriesBinding
import com.example.allrecipesfree_foodrecipesapp.ui.flow_country_categories_main.adapter.CountryVpAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.extensions.PagerTransformer
import com.example.allrecipesfree_foodrecipesapp.ui.extensions.HorizontalMarginItemDecoration
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter.MenuCategoryVpAdapter
import com.example.core.local.AppDataBase
import com.example.allrecipesfree_foodrecipesapp.ui.flow_menu_categories.adapter.PostsMenuRcAdapter
import com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail.PostsMenuDetailActivity
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.logD
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuCategoriesActivity : BaseActivity<ActivityMenuCategoriesBinding>() {

    private val viewModel: MenuCategoriesViewModel by viewModel()
    private lateinit var postsMenuRcAdapter: PostsMenuRcAdapter
    private val appData: AppDataBase by inject()
    private lateinit var listServiceResponse: List<ServiceResponse>
    private lateinit var menuCategoryVpAdapter: MenuCategoryVpAdapter

    override var layoutResource: Int = R.layout.activity_menu_categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id: Int = intent.getIntExtra("id", -1)

        setupToolbar()

        subscribeLiveData(id)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.app_name)
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    private fun subscribeLiveData(id: Int) {
        binding.viewModel = viewModel

        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))

        viewModel.fetchMenuCategories(id)

        viewModel.allMenuCategories.observe(this, Observer {
            DialogUtils.disMissDialog()
            if (it.isNotEmpty()) {
                listServiceResponse = it
                setupListMenu(it)
            } else {
                binding.vpView.visibility = View.GONE
                binding.rcView.visibility = View.GONE
                binding.tvMenuCateEmpty.visibility = View.VISIBLE
                binding.tvMenuCateEmpty.text = "Category is empty."
                binding.tvMenuEmpty.visibility = View.VISIBLE
                binding.tvMenuEmpty.text = getString(R.string.menu_is_empty)
            }
        })
    }

    private fun setupListMenu(it: List<ServiceResponse>?) {
        binding.vpView.visibility = View.VISIBLE
        binding.tvMenuCateEmpty.visibility = View.GONE

        menuCategoryVpAdapter = MenuCategoryVpAdapter(it!!, this)

        binding.vpView.apply {
            adapter = menuCategoryVpAdapter
            offscreenPageLimit = 1
            setPageTransformer(
                PagerTransformer(
                    this.resources.getDimension(R.dimen.viewpager_next_item),
                    this.resources.getDimension(R.dimen.viewpager_current_item_margin)
                )
            )
            addItemDecoration(
                HorizontalMarginItemDecoration(
                    this@MenuCategoriesActivity,
                    R.dimen.viewpager_current_item_margin
                )
            )
        }

        binding.vpView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                DialogUtils.showProgressDialog(this@MenuCategoriesActivity, getString(R.string.progress_msg))
                viewModel.fetchPostsMenu(it[position].id!!)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        viewModel.allPostsMenu.observe(this@MenuCategoriesActivity, Observer {
            DialogUtils.disMissDialog()

            if (it.isNotEmpty()) {
                setupRecyclerView(it)
            } else {
                binding.rcView.visibility = View.GONE
                binding.tvMenuEmpty.visibility = View.VISIBLE
                binding.tvMenuEmpty.text = getString(R.string.menu_is_empty)
            }
        })

    }

    private fun setupRecyclerView(it: List<ServiceResponse>?) {
        binding.rcView.visibility = View.VISIBLE
        binding.tvMenuEmpty.visibility = View.GONE

        postsMenuRcAdapter =
            PostsMenuRcAdapter(
                updateModel(
                    it!!,
                    appData.appDataBaseDao().getFavoriteMenu()
                ), this
            )
        binding.rcView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    this@MenuCategoriesActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = postsMenuRcAdapter
        }
        postsMenuRcAdapter.setOnClickPostsMenu(object : PostsMenuRcAdapter.OnClickPostsMenu {
            override fun onClickPostsMenu(postsMenu: ServiceResponse, position: Int) {
                startActivity(
                    Intent(
                        this@MenuCategoriesActivity,
                        PostsMenuDetailActivity::class.java
                    ).putExtra("id", postsMenu.id)
                )
                pageTransition()
            }

            override fun onClickFavoriteMenu(postsMenu: ServiceResponse, position: Int) {
                val t = appData.appDataBaseDao().getFavoriteMenu()
                    .filter { p -> (p.id == postsMenu.id) && (postsMenu.favoriteStatus == true) }
                if (t.isEmpty()) {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            postsMenu.id!!,
                            postsMenu.title!!.rendered!!,
                            true
                        )
                    )
                    postsMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                } else {
                    appData.appDataBaseDao().addFavoriteMenu(
                        Favorite(
                            postsMenu.id!!,
                            postsMenu.title!!.rendered!!,
                            false
                        )
                    )
                    postsMenuRcAdapter.updateData(
                        updateModel(
                            it,
                            appData.appDataBaseDao().getFavoriteMenu()
                        )
                    )
                }
            }

        })
    }

    private fun updateModel(
        serviceResponse: List<ServiceResponse>,
        favoriteMenu: List<Favorite>
    ): List<ServiceResponse> {
        favoriteMenu.forEach { f ->
            serviceResponse.find { it.id == f.id }?.favoriteStatus = f.status
        }
        return serviceResponse
    }

}