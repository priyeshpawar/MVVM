package com.example.mvvm.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.adapters.NotesAdapter
import com.example.mvvm.entities.NoteEntity
import com.example.mvvm.viewmodels.NotesListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesListActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesListViewModel

    private lateinit var rvNotes: RecyclerView
    private lateinit var fabAddNote: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        init()
        setDefaults()
        setEventHandlers()
    }

    private fun init() {

        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(NotesListViewModel::class.java)
        rvNotes = findViewById(R.id.rv_notes)
        fabAddNote = findViewById(R.id.fab_add_note)

    }

    private fun setDefaults() {
        rvNotes.layoutManager = LinearLayoutManager(this)
        viewModel.getAllNotes().observe(this, Observer<List<NoteEntity>> { notes ->
            if (notes != null) {
                for (note in notes) {
                    rvNotes.adapter = NotesAdapter(this, notes)
                }
            }
        })
    }

    private fun setEventHandlers() {
        fabAddNote.setOnClickListener { viewModel.showAddNoteDialog(this@NotesListActivity) }
    }
}
