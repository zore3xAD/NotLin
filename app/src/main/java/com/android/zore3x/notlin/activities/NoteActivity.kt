package com.android.zore3x.notlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.android.zore3x.notlin.App
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.data.Note
import com.android.zore3x.notlin.models.NoteModel
import com.android.zore3x.notlin.presenters.NotePresenter

import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.fragment_note.*

class NoteActivity : AppCompatActivity() {

    companion object {
        val POSITION_EXTRA = "position"

        fun newIntent(context: Context, position: Long): Intent {
            var intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(POSITION_EXTRA, position)
            return intent
        }
    }

    var presenter: NotePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        fab_editNote.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = NotePresenter(NoteModel(App.appDatabase.noteDao()))
        presenter?.attach(this)
        presenter?.viewIsReady()

    }

    override fun onDestroy() {
        presenter?.detach()
        super.onDestroy()
    }

    fun getNoteId() = intent.getLongExtra(POSITION_EXTRA, -1)

    fun show(data: Note) {
        note_titleText.text = data.title
        note_bodyText.text = data.body
        note_createDateText.text = data.getFormatDate()
    }

}
