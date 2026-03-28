package com.yeeeeni.presentation.ui.viewState

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}