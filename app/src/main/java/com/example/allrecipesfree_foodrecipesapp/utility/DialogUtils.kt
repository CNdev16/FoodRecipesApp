package com.example.allrecipesfree_foodrecipesapp.utility

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.example.allrecipesfree_foodrecipesapp.R
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.custom_dialog.view.tvMsg
import kotlinx.android.synthetic.main.custom_progress.view.*

//dialog utils.
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

        dialogLayout.btn.setOnClickListener {
            onClickButtonDialog.onClickButtonDialog()
        }
    }

    fun showProgressDialog(context: Context, msg: String){
        val progressLayout = LayoutInflater.from(context).inflate(R.layout.custom_progress, null)
        progressLayout.tvMsg.text = msg
        dialogProgress = Dialog(context)
        dialogProgress?.setContentView(progressLayout)
        dialogProgress?.setCancelable(false)
        dialogProgress?.show()
    }

    fun disMissDialog() {
        dialog?.dismiss()
        dialogProgress?.hide()
    }

    interface OnClickButtonDialog {
        fun onClickButtonDialog()
    }
}