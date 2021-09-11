package com.johnsapps.liverpoolsearching.util.extension

import java.text.NumberFormat
import java.util.*

fun Double.toPrice():String{
    val country = "MX"
    val language = "es"
    return NumberFormat.getCurrencyInstance(Locale(language,country)).format(this)
}