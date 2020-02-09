package com.example.mvvm.views

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.entities.NoteEntity
import com.example.mvvm.viewmodels.NotesListViewModel

class NotesListActivity : AppCompatActivity() {

    private val context: Application = Application();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        val notesListViewModel: NotesListViewModel =
            ViewModelProvider.AndroidViewModelFactory(application)
                .create(NotesListViewModel::class.java)

        notesListViewModel.getAllNotes()?.observe(this, object : Observer<List<NoteEntity>> {
            override fun onChanged(notes: List<NoteEntity>) {
                Log.d("NotesListActivity", notes.get(0).description)
            }
        })

    }
}
