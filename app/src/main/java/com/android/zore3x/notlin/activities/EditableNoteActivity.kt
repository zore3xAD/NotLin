package com.android.zore3x.notlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.fragments.EditableNoteFragment

import kotlinx.android.synthetic.main.activity_editable_note.*

class EditableNoteActivity : AppCompatActivity() {

    fun createFragment(): Fragment {
        if(intent.hasExtra(EXTRA_POSITION)) {
            return EditableNoteFragment.newInstance(intent.getLongExtra(EXTRA_POSITION, -1))
        } else {
            return EditableNoteFragment.newInstance()
        }
    }

    companion object {

        private const val EXTRA_POSITION = "EXTRA_POSITION"

        fun getIntent(context: Context) = Intent(context, EditableNoteActivity::class.java)
        fun getIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, EditableNoteActivity::class.java)
            intent.putExtra(EXTRA_POSITION, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editable_note)
        setSupportActionBar(toolbar)

        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.fragmentContainer)

        if (fragment == null) {
            fragment = createFragment()
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        }

        fab_saveNote.setOnClickListener { view ->
            if(intent.hasExtra(EXTRA_POSITION)) {
                val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                        as EditableNoteFragment
                fragment.update()
            } else {
                val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                        as EditableNoteFragment
                fragment.save()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
