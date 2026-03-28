package com.yeeeeni.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Diary 데이터 클래스
@Entity(tableName = "diary")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    //제목
    @ColumnInfo(name = "title")
    var title: String,

    //내용
    @ColumnInfo(name = "content")
    var content: String,

    //일기에 해당하는 날짜 (yyyy-mm-dd hh:mm)
    @ColumnInfo(name = "entryDate")
    var entryDate: String,

    //DB에 저장한 날짜 (yyyy-mm-dd hh:mm)
    @ColumnInfo(name = "createDate")
    var createDate: String,

    ) {
    constructor() : this(null, "", "","", "")
}
