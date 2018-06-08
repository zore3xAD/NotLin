package com.android.zore3x.notlin.data

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE


@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Long

    @Update(onConflict = IGNORE)
    fun update(note: Note)

    @Query("select * from notes")
    fun getAll(): MutableList<Note>

    @Query("select * from notes where _id = :id")
    fun getFromId(id: Long): Note
}