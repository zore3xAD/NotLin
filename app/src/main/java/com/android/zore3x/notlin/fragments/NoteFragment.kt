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
import com.android.zore3x.notlin.presenters.NotePresenter
import kotlinx.android.synthetic.main.fragment_note.*

/**
 * A placeholder fragment containing a simple view.
 */
class NoteFragment : Fragment() {

    companion object {
        private const val ID_ARGS = "id"

        fun newInstance(id: Long): Fragment {
            var fragment = NoteFragment()
            var args = Bundle()
            args.putLong(ID_ARGS, id)
            fragment.arguments = args

            return fragment
        }
    }

    var presenter: NotePresenter? = null
    var idNote: Long = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NotePresenter(NoteModel(App.appDatabase.noteDao()))
        presenter?.attach(this)

        if(arguments != null) {
            idNote = arguments[ID_ARGS] as Long
            presenter?.viewIsReady()
        }

        presenter?.viewIsReady()
    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    fun getNoteId() = arguments.getLong(ID_ARGS)

    fun show(data: Note) {
        note_titleText.text = data.title
        note_bodyText.text = data.body
        note_createDateText.text = data.getFormatDate()
    }
}
