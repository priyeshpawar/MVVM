package com.example.mvvm.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.mvvm.daos.NoteDao
import com.example.mvvm.database.AppDatabase
import com.example.mvvm.entities.NoteEntity

public class NoteRepository(context: Context) {

    private var noteDao: NoteDao = AppDatabase.getDatabaseInstance(context).noteDao()
    private var allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    public fun insert(note: NoteEntity) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    public fun update(note: NoteEntity) {
        UpdateNoteAsyncTast(noteDao).execute(note)
    }

    public fun delete(note: NoteEntity) {
        DeleteNoteAsyncTast(noteDao).execute(note)
    }

    public fun getAllNotes(): LiveData<List<NoteEntity>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(private var noteDao: NoteDao) :
        AsyncTask<NoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: NoteEntity): Void? {
            noteDao.insertNote(params[0])
            return null
        }

    }

    private class UpdateNoteAsyncTast(private var noteDao: NoteDao) :
        AsyncTask<NoteEntity, Void, Void>() {

        override fun doInBackground(vararg params: NoteEntity): Void? {
            noteDao.updateNote(params[0])
            return null
        }

    }

    private class DeleteNoteAsyncTast(private var noteDao: NoteDao) :
        AsyncTask<NoteEntity, Void, Void>() {
        override fun doInBackground(vararg params: NoteEntity): Void? {
            noteDao.deleteNote(params[0])
            return null
        }

    }

}