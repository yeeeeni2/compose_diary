package com.yeeeeni.presentation.ui.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDate() : String {
    return SimpleDateFormat(
        "yyyyMMdd",
        Locale.getDefault()
    ).format(Date(this))
}