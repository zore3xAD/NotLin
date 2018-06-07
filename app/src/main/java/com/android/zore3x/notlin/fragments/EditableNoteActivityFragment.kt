package com.android.zore3x.notlin.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.zore3x.notlin.App
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.models.NoteModel
import com.android.zore3x.notlin.presenters.NoteEditablePresenter
import kotlinx.android.synthetic.main.fragment_editable_note.*
import org.jetbrains.anko.support.v4.toast

/**
 * A placeholder fragment containing a simple view.
 */
class EditableNoteActivityFragment : Fragment() {

    var presenter: NoteEditablePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_editable_note, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NoteEditablePresenter(NoteModel(App.appDatabase.noteDao()))
        presenter?.attach(this)
    }


    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    fun showToast(result: String) {
        toast("Note added. ID = $result")
    }

    fun getData(): Note = Note(note_titleEdit.text.toString(), note_bodyEdit.text.toString())

    fun close() {activity.finish()}

    fun save() { presenter?.insert()}
}
