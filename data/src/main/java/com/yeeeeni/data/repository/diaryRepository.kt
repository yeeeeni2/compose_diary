package com.yeeeeni.data.repository

import com.yeeeeni.data.local.dao.DiaryDao
import com.yeeeeni.data.local.entity.Diary
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao
) {
    fun getAll(): Flow<List<Diary>> = diaryDao.getAll()

    fun getDiary(id: Int): Flow<Diary> = diaryDao.getDiary(id)

    suspend fun insert(diary: Diary) = diaryDao.insert(diary)

    suspend fun update(diary: Diary) = diaryDao.update(diary)

    suspend fun delete(id: Int) = diaryDao.deleteDiary(id)

    suspend fun deleteAll() = diaryDao.deleteAll()
}