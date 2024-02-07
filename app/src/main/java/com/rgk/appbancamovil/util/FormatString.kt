package com.rgk.appbancamovil.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


object FormatString {
    fun parseAndFormatDate(inputDate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

            val date = inputFormat.parse(inputDate)
            outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
    fun copyToClipboard(context: Context, textToCopy: String, label: String = "Copiado") {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(label, textToCopy)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(context, "Número de cuenta copiado al portapapeles", Toast.LENGTH_SHORT).show()
    }
}