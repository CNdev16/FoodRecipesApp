package com.example.allrecipesfree_foodrecipesapp.utility

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.example.allrecipesfree_foodrecipesapp.R
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.custom_dialog.view.tvMsg
import kotlinx.android.synthetic.main.custom_progress.view.*

object DialogUtils {

    private var alertDialog: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null
    private var dialogProgress: Dialog? = null

    fun showDialogOneButton(
        context: Context,
        titleMsg: String,
        detailMsg: String,
        labelBtn: String,
        onClickButtonDialog: OnClickButtonDialog
    ) {
        val dialogLayout = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
        alertDialog = AlertDialog.Builder(context).setView(dialogLayout).setCancelable(false)

        dialogLayout.tvTitle.text = titleMsg
        dialogLayout.tvMsg.text = detailMsg
        dialogLayout.btn.text = labelBtn
        dialog = alertDialog!!.show()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogLayout.btn.setOnClickListener {
            onClickButtonDialog.onClickButtonDialog()
        }
    }

    fun showProgressDialog(context: Context, msg: String) {
        val progressLayout = LayoutInflater.from(context).inflate(R.layout.custom_progress, null)
        progressLayout.tvMsg.apply {
            text = msg
            visibility = View.GONE
        }
        dialogProgress = Dialog(context)
        dialogProgress?.setContentView(progressLayout)
        dialogProgress?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogProgress?.setCancelable(false)
        dialogProgress?.show()
    }

    fun disMissDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog?.dismiss()
        }

        if (dialogProgress != null && dialogProgress!!.isShowing) {
            dialogProgress?.dismiss()
        }
    }

    interface OnClickButtonDialog {
        fun onClickButtonDialog()
    }
}