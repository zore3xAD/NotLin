package com.android.zore3x.notlin.presenters

import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.fragments.EditableNoteFragment
import com.android.zore3x.notlin.models.NoteModel

class NoteEditablePresenter(val model: NoteModel) {


    var view: EditableNoteFragment? = null

    fun attach(view: EditableNoteFragment) { this.view = view }

    fun detach() { this.view = null }

    fun insert() {
        model.insert(view?.getData()!!, object : NoteContract.OnInsertCallback {
            override fun onComplete(id: Long) {
                view?.showToast(id.toString())
                view?.close()
            }

            override fun onError(message: String) {
                view?.showToast(message)
            }
        })
    }

    fun update() {
        val data = view?.getData()
        model.update(view?.getData()!!, object : NoteContract.OnUpdateCallback {
            override fun onComplete() {
                view?.showToast("Update")
                view?.close()
            }

            override fun onError() {
                view?.showToast("error")
            }

        })
    }

    fun viewIsReady() {
        load(view?.getLongId()!!)
    }
    fun load(id: Long) {
        model.selectFromId(id, object : NoteContract.OnSelectCallback {
            override fun onComplete(data: Note) {
                view?.show(data)
            }
        })
    }
}