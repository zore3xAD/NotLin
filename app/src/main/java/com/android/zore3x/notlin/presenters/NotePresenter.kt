package com.android.zore3x.notlin.presenters

import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.fragments.NoteFragment
import com.android.zore3x.notlin.models.NoteModel

class NotePresenter(val model: NoteModel) {

    var view: NoteFragment? = null

    lateinit var note: Note

    fun attach(view: NoteFragment) { this.view = view}
    fun detach() {this.view = null}
    fun viewIsReady() {loadNote()}

    fun loadNote() {
        val id = view?.getNoteId()
        model.selectFromId(id!!, object : NoteContract.OnSelectCallback {
            override fun onError(message: String) {
                view?.showToast(message)
            }

            override fun onComplete(data: Note) {
                note = data
                view?.show(note)
            }
        })
    }

    fun delete() {
        model.delete(note, object : NoteContract.OnDeleteCallback {
            override fun onComplete() {
                view?.showToast("Deleted")
                view?.close()
            }

            override fun onError() {
                view?.showToast("error")
            }
        })
    }
}