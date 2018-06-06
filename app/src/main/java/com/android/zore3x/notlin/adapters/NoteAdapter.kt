package com.android.zore3x.notlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.zore3x.notlin.R
import com.android.zore3x.notlin.data.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    var data: MutableList<Note> = ArrayList()
        set(value) {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteHolder =
            NoteHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_note, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NoteHolder?, position: Int) { holder?.bind(data[position]) }


    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var noteTitle: TextView
        var noteBody: TextView
        var noteCreateDate: TextView

        init {
            noteTitle = itemView.findViewById(R.id.card_noteTitle)
            noteBody = itemView.findViewById(R.id.card_noteBody)
            noteCreateDate = itemView.findViewById(R.id.card_noteCreateDate)
        }

        fun bind(value: Note) {
            noteTitle.text = value.title
            noteBody.text = value.body
            noteCreateDate.text = value.getFormatDate()
        }

    }
}