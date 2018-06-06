package com.android.zore3x.notlin.presenters

import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.fragments.EditableNoteActivityFragment
import com.android.zore3x.notlin.models.NoteModel

class NotePresenter(val model: NoteModel) {


    var view: EditableNoteActivityFragment? = null

    fun attach(view: EditableNoteActivityFragment) { this.view = view }

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
}