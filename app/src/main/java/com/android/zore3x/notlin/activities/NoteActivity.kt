package com.android.zore3x.notlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.fragments.NoteFragment

import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    fun createFragment(): Fragment {

        val id= intent.getLongExtra(EXTRA_ID, -1)

        return NoteFragment.newInstance(id)
    }

    companion object {
        private const val EXTRA_ID = "id"

        fun getIntent(context: Context, position: Long): Intent {
            var intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_ID, position)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setSupportActionBar(toolbar)

        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.fragmentContainer)

        if (fragment == null) {
            fragment = createFragment()
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        }

        fab_editNote.setOnClickListener { view ->
            startActivity(EditableNoteActivity.getIntent(applicationContext, intent.extras[EXTRA_ID] as Long))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
