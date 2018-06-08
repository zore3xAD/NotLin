package com.android.zore3x.notlin.presenters

import com.android.zore3x.notlin.contracts.NoteContract
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.fragments.NoteListFragment
import com.android.zore3x.notlin.models.NoteModel

class NoteListPresenter(val model: NoteModel) {

    var view: NoteListFragment? = null

    fun attach(view: NoteListFragment) { this.view = view}

    fun detach() {this.view = null}

    fun viewIsReady() { loadData()}

    fun loadData() {
        model.selectAll(object : NoteContract.OnLoadCallback {
            override fun onComplete(data: MutableList<Note>) {
                view?.show(data)
            }

            override fun onError(message: String) {
                view?.showToast(message)
            }

        })
    }

}