package com.yeeeeni.presentation.ui.extension

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.toLongMillisecond() : Long {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return formatter.parse(this)?.time ?: System.currentTimeMillis()
}

fun String.toKoreanDateFormat(): String {
    if(this.isEmpty()) return ""
    val inputFormatter = SimpleDateFormat("yyyyMMdd", Locale.KOREAN)
    val outputFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN)
    return inputFormatter.parse(this)?.let { outputFormatter.format(it) } ?: this
}