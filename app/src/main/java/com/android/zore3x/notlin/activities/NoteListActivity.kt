package com.android.zore3x.notlin.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.fragments.NoteListFragment

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    fun createFragment(): Fragment {
        return NoteListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.fragmentContainer)

        if (fragment == null) {
            fragment = createFragment()
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        }

        fab_addNote.setOnClickListener { view ->
            startActivity(EditableNoteActivity.getIntent(applicationContext))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_note_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
