package com.android.zore3x.notlin.contracts

import com.android.zore3x.notlin.data.Note

interface NoteContract {

    interface OnLoadCallback {
        fun onComplete(data: MutableList<Note>)
        fun onError(message: String)
    }

    interface OnSelectCallback {
        fun onComplete(data: Note)
        fun onError(message: String)
    }

    interface OnInsertCallback {
        fun onComplete(id: Long)
        fun onError(message: String)
    }

    interface OnUpdateCallback {
        fun onComplete()
        fun onError()
    }

    interface OnDeleteCallback {
        fun onComplete()
        fun onError()
    }
}