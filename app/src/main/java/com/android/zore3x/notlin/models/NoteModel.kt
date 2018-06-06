package com.android.zore3x.notlin.models

import android.os.AsyncTask
import com.android.zore3x.notlin.App
import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.data.NoteDao

class NoteModel(val db: NoteDao) {

    fun insert(data: Note, callback: NoteContract.OnInsertCallback) {
        InsertTask(callback).execute(data)
    }

    fun select(callback: NoteContract.OnLoadCallback) {
        SelectTask(callback).execute("")
    }


    internal inner class InsertTask(var callback: NoteContract.OnInsertCallback) :
            AsyncTask<Note, Void, Long>() {
        override fun doInBackground(vararg p0: Note?): Long {
            var noteID: Long = 0
            for(data in p0) {
                noteID = db.insert(data!!)
            }
            return noteID
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            when(result) {
                -1.toLong() -> callback.onError("Item not added")
                else -> callback.onComplete(result!!)
            }
        }
    }

    internal inner class SelectTask(var callback: NoteContract.OnLoadCallback) :
            AsyncTask<String, Void, MutableList<Note>>() {
        override fun doInBackground(vararg p0: String?): MutableList<Note> {
            return db.getAll()
        }

        override fun onPostExecute(result: MutableList<Note>?) {
            super.onPostExecute(result)
            if (result != null) {
                callback.onComplete(result)
            } else {
                callback.onError("Error")
            }
        }

    }
}