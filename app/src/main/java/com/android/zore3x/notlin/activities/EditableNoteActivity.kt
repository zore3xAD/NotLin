package com.android.zore3x.notlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.fragments.EditableNoteActivityFragment

import kotlinx.android.synthetic.main.activity_editable_note.*

class EditableNoteActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, EditableNoteActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editable_note)
        setSupportActionBar(toolbar)

        fab_saveNote.setOnClickListener { view ->
            var fragment = supportFragmentManager.findFragmentById(R.id.fragmentEditableNote) as EditableNoteActivityFragment
            fragment.save()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
