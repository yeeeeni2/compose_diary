package com.yeeeeni.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeeeeni.data.local.entity.Diary
import com.yeeeeni.data.repository.DiaryRepository
import com.yeeeeni.presentation.ui.viewState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    private val _diaryListState = MutableStateFlow<UiState<List<Diary>>>(UiState.Loading)
    val diaryListState: StateFlow<UiState<List<Diary>>> = _diaryListState.asStateFlow()

    private val _diaryState = MutableStateFlow<UiState<Diary>>(UiState.Loading)
    val diaryState: StateFlow<UiState<Diary>> = _diaryState.asStateFlow()

    init {
        fetchDiaryList()
    }

    private fun fetchDiaryList() {
        viewModelScope.launch {
            _diaryListState.value = UiState.Loading

            ///화면 깜빡임 방지(api 응답시간이 300ms 이하일 경우 최소 지연 보장)
            val startTime = System.currentTimeMillis()

            repository.getAll()
                .catch { e -> _diaryListState.value = UiState.Error(e) }
                .collect { list ->
                    val elapsed = System.currentTimeMillis() - startTime
                    val remaining = 300L - elapsed
                    if (remaining > 0) delay(remaining)

                    _diaryListState.value = if (list.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(list)
                    }
                }
        }
    }

    fun getDiary(id: Int) {
        viewModelScope.launch {
            _diaryState.value = UiState.Loading
            repository.getDiary(id)
                .catch { e -> _diaryState.value = UiState.Error(e) }
                .collect { diary ->
                    _diaryState.value = UiState.Success(diary)
                }
        }
    }

    fun retry() {
        fetchDiaryList()
    }

    fun insert(diary: Diary) {
        viewModelScope.launch {
            runCatching { repository.insert(diary) }
                .onFailure { e -> _diaryListState.value = UiState.Error(e) }
        }
    }

    fun update(diary: Diary) {
        viewModelScope.launch {
            runCatching { repository.update(diary) }
                .onFailure { e -> _diaryListState.value = UiState.Error(e) }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            runCatching { repository.delete(id) }
                .onFailure { e -> _diaryListState.value = UiState.Error(e) }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            runCatching { repository.deleteAll() }
                .onFailure { e -> _diaryListState.value = UiState.Error(e) }
        }
    }
}