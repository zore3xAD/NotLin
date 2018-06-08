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
class EditableNoteFragment : Fragment() {

    companion object {

        private const val ID_ARG = "id"

        fun newInstance(): Fragment = EditableNoteFragment()
        fun newInstance(id: Long): Fragment {
            val fragment = EditableNoteFragment()
            val args = Bundle()
            args.putLong(ID_ARG, id)
            fragment.arguments = args

            return fragment
        }
    }

    var presenter: NoteEditablePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_editable_note, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NoteEditablePresenter(NoteModel(App.appDatabase.noteDao()))
        presenter?.attach(this)

        if(arguments != null) {
            val id = arguments[ID_ARG] as Long
            if (id != null) {
                presenter?.viewIsReady(id)
            }
        }
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    fun show(data: Note) {
        note_titleEdit.setText(data.title)
        note_bodyEdit.setText(data.body)
    }

    fun showToast(result: String) {
        toast("Note added. ID = $result")
    }

    fun getData(): Note = Note(note_titleEdit.text.toString(), note_bodyEdit.text.toString())

    fun close() {activity.finish()}

    fun save() { presenter?.insert()}
}
