package com.example.mvvm.database

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm.daos.NoteDao
import com.example.mvvm.entities.NoteEntity

@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getDatabaseInstance(application: Application): AppDatabase {
            var db: AppDatabase? = dbInstance

            if (db == null) {
                db = Room.databaseBuilder(
                    application, AppDatabase::class.java, "mvvm_database"
                ).addCallback(roomCallback).build();
            }
            return db;
        }

        private var roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateNotesAsyncTask(dbInstance?.noteDao()).execute()
            }
        }

        private class PopulateNotesAsyncTask(private var noteDao: NoteDao?) :
            AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void): Void? {
                noteDao?.insertNote(NoteEntity("Title 1", "sekrnvwaekbjgkwauebgkwabeg", false))
                noteDao?.insertNote(NoteEntity("Title 2", "sekrnvwaekbjgkwauebgkwabeg", false))
                noteDao?.insertNote(NoteEntity("Title 3", "sekrnvwaekbjgkwauebgkwabeg", false))
                return null
            }
        }

    }

    abstract fun noteDao(): NoteDao


}