package com.yeeeeni.presentation.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yeeeeni.data.repository.DiaryRepository
import com.yeeeeni.presentation.ui.viewState.CustomDatePickerDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle?,
) : ViewModel() {

    val customDatePickerDialogState: MutableState<CustomDatePickerDialogState?> =
        mutableStateOf(null)

    init {
        customDatePickerDialogState.value = CustomDatePickerDialogState(
            onClickConfirm = { yyyyMMdd ->
                customDatePickerDialogState.value = customDatePickerDialogState.value?.copy(
                    isShowDialog = false,
                    selectedDate = yyyyMMdd
                )
                repository.updateSelectedDate(yyyyMMdd)
            },
            onClickCancel = {
                customDatePickerDialogState.value = customDatePickerDialogState.value?.copy(
                    isShowDialog = false
                )
            }
        )
    }

    fun showDatePickerDialog() {
        customDatePickerDialogState.value =
            customDatePickerDialogState.value?.copy(isShowDialog = true)
    }
}