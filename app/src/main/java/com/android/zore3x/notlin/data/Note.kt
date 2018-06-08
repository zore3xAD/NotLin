package com.android.zore3x.notlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.provider.BaseColumns
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "notes")
class Note : BaseColumns {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0
    @ColumnInfo(name = "title")
    var title: String
    @ColumnInfo(name = "body")
    var body: String
    @ColumnInfo(name = "createDate")
    var createDateLong: Long
    @Ignore
    var createDate: Date

    constructor(){
        this.body = ""
        this.title = ""
        this.createDate = Date()
        this.createDateLong = createDate.time
    }
    constructor(title: String, body: String, createDate: Long) {
        this.title = title
        this.body = body
        this.createDateLong = createDate
        this.createDate = Date(createDateLong)
    }
    constructor(title: String, body: String) {
        this.title = title
        this.body = body
        this.createDate = Date()
        this.createDateLong = createDate.time
    }

    fun getFormatDate(): String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(this.createDate)
}