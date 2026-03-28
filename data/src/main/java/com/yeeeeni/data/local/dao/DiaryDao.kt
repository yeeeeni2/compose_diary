package com.yeeeeni.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yeeeeni.data.local.entity.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    // diary 테이블의 데이터와 상호작용하는 데 사용하는 메서드를 제공
    @Query("SELECT * FROM diary ORDER BY entryDate DESC")
    fun getAll(): Flow<List<Diary>>

    @Query("SELECT * FROM diary WHERE id = :id")
    fun getDiary(id: Int) :Flow<Diary>

    // id가 중복될 경우 대체
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Diary)

    @Update
    suspend fun update(diary: Diary)

    @Query("DELETE FROM diary WHERE id = :id")
    suspend fun deleteDiary(id: Int)

    @Query("DELETE FROM diary")
    suspend fun deleteAll()
}