package com.yeeeeni.presentation.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.yeeeeni.presentation.ui.extension.clickableNoRipple
import com.yeeeeni.presentation.ui.extension.today
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import androidx.compose.material3.SelectableDates
import com.yeeeeni.presentation.ui.common.Pink400
import com.yeeeeni.presentation.ui.extension.toDate
import com.yeeeeni.presentation.ui.extension.toLongMillisecond

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    selectedDate: String?,
    onClickCancel: () -> Unit,
    onClickConfirm: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        yearRange = 2025..2026,
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = selectedDate?.toLongMillisecond() ?: System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year <= LocalDateTime.now().year
            }
        }
    )

    DatePickerDialog(
        onDismissRequest = { onClickCancel() },
        confirmButton = {
            Button(
                onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                        onClickConfirm(selectedDateMillis.toDate())
                    }
                }) {
                Text(
                    text = "확인",
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onClickCancel() }) {
                Text(
                    text = "취소",
                )
            }
        },
        shape = RoundedCornerShape(6.dp)
    ) {
        DatePicker(state = datePickerState)
    }
}