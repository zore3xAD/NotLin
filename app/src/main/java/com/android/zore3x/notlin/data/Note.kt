package com.android.zore3x.notlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note {

    constructor(){
        this.text = ""
        this.title = ""
        this.createDate = Date()
        this.createDateLong = createDate.time
    }
    constructor(title: String, text: String, createDate: Long) {
        this.title = title
        this.text = text
        this.createDateLong = createDate
        this.createDate = Date(createDateLong)
    }
    constructor(title: String, text: String) {
        this.title = title
        this.text = text
        this.createDate = Date()
        this.createDateLong = createDate.time
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0
    @ColumnInfo(name = "title")
    var title: String
    @ColumnInfo(name = "text")
    var text: String
    @ColumnInfo(name = "createDate")
    var createDateLong: Long
    @Ignore
    var createDate: Date

}