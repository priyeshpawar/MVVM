package com.example.mvvm.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.mvvm.daos.NoteDao
import com.example.mvvm.database.AppDatabase
import com.example.mvvm.entities.NoteEntity

class NoteRepository(application: Application) {

    private var noteDao: NoteDao = AppDatabase.getDatabaseInstance(application).noteDao()
    private var rowCount: Int = 0

    fun insert(note: NoteEntity) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: NoteEntity) {
        UpdateNoteAsyncTast(noteDao).execute(note)
    }

    fun delete(note: NoteEntity?) {
        DeleteNoteAsyncTast(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTast(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    fun getCount(): Int {
        GetCountAsyncTask(noteDao, rowCount).execute()
        return rowCount
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

    private class GetCountAsyncTask(
        var noteDao: NoteDao, var rowCount: Int
    ) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            rowCount = noteDao.getCount()
            return null
        }
    }

    private class DeleteAllNotesAsyncTast(private var noteDao: NoteDao) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            noteDao.deleteAllNote()
            return null
        }
    }

}