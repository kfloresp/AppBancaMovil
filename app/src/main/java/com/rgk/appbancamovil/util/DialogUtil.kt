package com.rgk.appbancamovil.util

import android.app.AlertDialog
import android.content.Context
import com.rgk.appbancamovil.R

object DialogUtil {

    fun showAlertRetryAccount(context: Context, title: String, message: String,listener: AlertRetryAccountInterface) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.title_retry_accounts)) { dialog, _ ->
                listener.onClickRetryAccount()
                dialog.dismiss()
            }
            .show()
    }
    fun showAlertAcept(context: Context, title: String, message: String,listener: AlertAceptRetryAccountInterface) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.title_acept)) { dialog, _ ->
                listener.onClickAceptRetryAccount()
                dialog.dismiss()
            }
            .show()
    }

    interface AlertRetryAccountInterface{
        fun onClickRetryAccount()
    }
    interface AlertAceptRetryAccountInterface{
        fun onClickAceptRetryAccount()
    }

}