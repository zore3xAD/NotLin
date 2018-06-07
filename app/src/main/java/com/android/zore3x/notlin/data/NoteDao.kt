package com.android.zore3x.notlin.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Query("select * from notes")
    fun getAll(): MutableList<Note>

    @Query("select * from notes where _id = :id")
    fun getFromId(id: Long): Note
}