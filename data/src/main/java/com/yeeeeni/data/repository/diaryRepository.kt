package com.yeeeeni.data.repository

import com.yeeeeni.data.local.dao.DiaryDao
import com.yeeeeni.data.local.entity.Diary
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao
) {

    // 선택된 날짜
    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    fun updateSelectedDate(yyyyMMdd: String) {
        _selectedDate.value = yyyyMMdd
    }

    fun getAll(): Flow<List<Diary>> = diaryDao.getAll()

    fun getDiary(id: Int): Flow<Diary> = diaryDao.getDiary(id)

    suspend fun insert(diary: Diary) = diaryDao.insert(diary)

    suspend fun update(diary: Diary) = diaryDao.update(diary)

    suspend fun delete(id: Int) = diaryDao.deleteDiary(id)

    suspend fun deleteAll() = diaryDao.deleteAll()
}