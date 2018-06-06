package com.android.zore3x.notlin.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.zore3x.notlin.App
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.adapters.NoteAdapter
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.models.NoteModel
import com.android.zore3x.notlin.presenters.NoteListPresenter
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.jetbrains.anko.support.v4.toast

/**
 * A placeholder fragment containing a simple view.
 */
class NoteListActivityFragment : Fragment() {

    lateinit var adapter: NoteAdapter
    var presenter: NoteListPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NoteAdapter()

        noteList.layoutManager = LinearLayoutManager(activity.applicationContext)
        noteList.adapter = adapter

        presenter = NoteListPresenter(NoteModel(App.appDatabase.noteDao()))
        presenter?.attach(this)
        presenter?.viewIsReady()
    }

    override fun onResume() {
        presenter?.viewIsReady()
        super.onResume()
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    fun showToast(message: String) {
        toast(message)
    }

    fun show(data: MutableList<Note>) {
        adapter.data = data
    }

}
