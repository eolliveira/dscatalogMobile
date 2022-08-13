package com.example.dscatalogmobile.utils

import java.text.NumberFormat
import java.util.*

class FormatCurrency {
    companion object {
        fun real(value: Double?): String {
            val currency = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return currency.format(value)
        }
    }
}
