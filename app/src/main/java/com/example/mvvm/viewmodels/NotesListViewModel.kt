package com.example.mvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm.entities.NoteEntity
import com.example.mvvm.repositories.NoteRepository

public class NotesListViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository
    private var allNotes: LiveData<List<NoteEntity>>?

    init {
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.getAllNotes()
    }

    public fun insert(note: NoteEntity) {
        noteRepository.insert(note)
    }

    public fun update(note: NoteEntity) {
        noteRepository.delete(note)
    }

    public fun delete(note: NoteEntity) {
        noteRepository.delete(note)
    }

    public fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }

    public fun getAllNotes(): LiveData<List<NoteEntity>>? {
        return allNotes
    }


}