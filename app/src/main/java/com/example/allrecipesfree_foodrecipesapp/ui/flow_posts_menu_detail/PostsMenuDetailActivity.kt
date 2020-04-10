package com.example.allrecipesfree_foodrecipesapp.ui.flow_posts_menu_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.base.BaseActivity
import com.example.allrecipesfree_foodrecipesapp.databinding.ActivityPostsMenuDetailBinding
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostsMenuDetailActivity : BaseActivity<ActivityPostsMenuDetailBinding>() {

    private val viewModel: PostsMenuDetailViewModel by viewModel()
    private lateinit var tvTitle: TextView
    override var layoutResource: Int = R.layout.activity_posts_menu_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postsId = intent.getIntExtra("id", -1)

        setupToolbar()
        subscribeLiveData(postsId)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar!!
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayShowTitleEnabled(false)

        val cusView = LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null)
        val ivBack = cusView.findViewById<ImageView>(R.id.ivBack)
        tvTitle = cusView.findViewById<TextView>(
            R
                .id.tvTitleToolbar
        )
        ivBack.apply {
            setOnClickListener { onBackPressed() }
        }
        actionBar.customView = cusView
        actionBar.setDisplayShowCustomEnabled(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun subscribeLiveData(postsId: Int) {
        binding.viewModel = viewModel

        DialogUtils.showProgressDialog(this, getString(R.string.progress_msg))
        viewModel.getPostsMenuDetail(postsId)

        viewModel.postsMenuDetail.observe(this, Observer {
            DialogUtils.disMissDialog()

            tvTitle.apply {
                text = "${it.title!!.rendered}"
            }

            binding.webViewDetail.apply {
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.domStorageEnabled = true
                setBackgroundColor(0x00000000)
                settings.defaultFontSize = 24

//                loadData(
//                    "<html><head><style type='text/css'>body{margin-top:auto; margin-bottom:auto;} img{width:100%25;} </style></head><body>${it.content?.rendered}</body></html>",
//                    "text/html",
//                    "utf-8"
//                )
                loadData(
                    "<html>"+"${getStyledFont(it.content?.rendered!!)}"+"</html>",
                    "text/html",
                    "utf-8"
                )
            }
        })
    }

    private fun getStyledFont(html: String): String? {
        val addBodyStart = !html.toLowerCase().contains("<body>")
        val addBodyEnd = !html.toLowerCase().contains("</body")
        return "<style type=\"text/css\">@font-face {font-family: CustomFont;" +
                "src: url(\"file:///android_assets/fonts/Sarabun-Regular.ttf\")}" +
                "body {font-family: CustomFont;font-size: large;text-align: justify;}</style>" +
                (if (addBodyStart) "<body>" else "") + html + if (addBodyEnd) "</body>" else ""
    }
}