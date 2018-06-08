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
            presenter?.viewIsReady()
        }
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    override fun onResume() {
        presenter?.viewIsReady()
        super.onResume()
    }

    fun show(data: Note) {
        note_titleEdit.setText(data.title)
        note_bodyEdit.setText(data.body)
    }

    fun showToast(result: String) {
        toast(result)
    }

    fun getData(): Note {
        var data = Note(note_titleEdit.text.toString(), note_bodyEdit.text.toString())
        if(arguments.isEmpty) {
            return data
        }
        data.id = arguments[ID_ARG] as Long
        return data
    }

    fun close() {activity.finish()}

    fun save() { presenter?.insert()}

    fun update() { presenter?.update()}

    fun getLongId() = arguments[ID_ARG] as Long
}
