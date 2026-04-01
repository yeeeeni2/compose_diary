package com.yeeeeni.presentation.ui.extension


import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

// 오늘 날짜 가져오기
fun today(): String = LocalDateTime.now().toDateFormat()

//yyyy-MM-dd 날짜 형식
fun Date.toDefaultFormat(): String {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.KOREAN)
    return formatter.format(this)
}

///한국식 날짜 표기(ex. 2025년 09월 09일)
fun LocalDateTime.toKoreanFormat(): String {
    val formatter = DateTimeFormatter.ofPattern(
        "yyyy년 MM월 dd일",
        Locale.KOREAN
    )
    return this.format(formatter)
}

fun LocalDateTime.toDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern(
        "yyyMMdd",
        Locale.KOREAN
    )
    return this.format(formatter)
}