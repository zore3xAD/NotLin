package com.android.zore3x.notlin.presenters

import com.android.zore3x.notlin.activities.NoteActivity
import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.models.NoteModel

class NotePresenter(val model: NoteModel) {

    var view: NoteActivity? = null

    fun attach(view: NoteActivity) { this.view = view}
    fun detach() {this.view = null}
    fun viewIsReady() {loadNote()}
    fun loadNote() {
        val id = view?.getNoteId()
        model.selectFromId(id!!, object : NoteContract.OnSelectCallback {
            override fun onComplete(data: Note) {
                view?.show(data)
            }
        })
    }
}