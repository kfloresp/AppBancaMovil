package com.rgk.appbancamovil.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.Locale

object FormatMoneyUtil {
    fun formatMoney(amount: Float?): String {
        if (amount == null) {
            return ""
        }

        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formattedAmount = currencyFormat.format(amount.toDouble())

        val decimalFormat = DecimalFormat("#,##0.00")
        val formattedString = formattedAmount.replace(currencyFormat.currency.symbol, "")
            .replace(currencyFormat.currency.getSymbol(Locale.getDefault()), "")
            .trim()

        return try {
            decimalFormat.parse(formattedString)?.toFloat()?.let {
                decimalFormat.format(it)
            } ?: ""
        } catch (e: ParseException) {
            ""
        }
    }
}
