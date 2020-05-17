package com.example.allrecipesfree_foodrecipesapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.allrecipesfree_foodrecipesapp.R
import com.example.allrecipesfree_foodrecipesapp.utility.DialogUtils
import com.example.allrecipesfree_foodrecipesapp.utility.isInternetConnected

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(){

    abstract var layoutResource: Int
    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireActivity().isInternetConnected()) {
            loading()
            subscribeLiveData()
            handleError()
        } else {
            DialogUtils.showDialogOneButton(
                requireContext(),
                getString(R.string.dialog_title),
                getString(R.string.no_internet),
                getString(R.string.dialog_btn),
                object : DialogUtils.OnClickButtonDialog {
                    override fun onClickButtonDialog() {
                        DialogUtils.disMissDialog()
                        activity!!.finishAffinity()
                    }
                })
        }
    }

    open fun subscribeLiveData() {}

    open fun loading() {}

    open fun handleError() {}
}