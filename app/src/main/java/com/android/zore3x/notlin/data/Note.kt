package com.android.zore3x.notlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Long = 0,
        @ColumnInfo(name = "text")
        var text: String,
        @ColumnInfo(name = "createDate")
        var createDate: Date
) {

    constructor(text: String, createDate: Date) : this(0, text, createDate) {
        this.text = text
        this.createDate = createDate
    }
}