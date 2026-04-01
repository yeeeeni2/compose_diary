package com.yeeeeni.presentation.ui.viewState

data class CustomDatePickerDialogState(
   var selectedDate: String? = null,
   var isShowDialog: Boolean = false,
   val onClickConfirm: (String) -> Unit = {},
   val onClickCancel: () -> Unit = {},
)