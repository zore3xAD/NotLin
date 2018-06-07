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

    fun selectAll(callback: NoteContract.OnLoadCallback) {
        SelectAllTask(callback).execute()
    }

    fun selectFromId(id: Long, callback: NoteContract.OnSelectCallback) {
        SelectFromIdTask(callback).execute(id)
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

    internal inner class SelectFromIdTask(var callback: NoteContract.OnSelectCallback) :
            AsyncTask<Long, Void, Note>() {
        override fun doInBackground(vararg p0: Long?): Note {
            return db.getFromId(p0[0]!!)
        }

        override fun onPostExecute(result: Note?) {
            super.onPostExecute(result)
            callback.onComplete(result!!)
        }
    }

    internal inner class SelectAllTask(var callback: NoteContract.OnLoadCallback) :
            AsyncTask<Void, Void, MutableList<Note>>() {
        override fun doInBackground(vararg p0: Void?): MutableList<Note>? {
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