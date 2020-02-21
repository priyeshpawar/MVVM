package com.example.mvvm.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.daos.NoteDao
import com.example.mvvm.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getDatabaseInstance(application: Application): AppDatabase {
            var db: AppDatabase? = dbInstance

            if (db == null) {
                db = Room.databaseBuilder(application, AppDatabase::class.java, "mvvm_database")
//                    .addMigrations(migration_1_2, migration_2_3)
//                    .addCallback(roomCallback)
                    .build()
            }
            dbInstance = db
            return db
        }
//
//        var migration_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE tb_note ADD COLUMN priority INTEGER DEFAULT 0 NOT NULL")
//            }
//        }
//
//        var migration_2_3: Migration = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE tb_note ADD COLUMN something TEXT DEFAULT 0 NOT NULL")
//            }
//        }

//        private var roomCallback: Callback = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                PopulateNotesAsyncTask(dbInstance?.noteDao()).execute()
//            }
//        }
//
//        private class PopulateNotesAsyncTask(private var noteDao: NoteDao?) :
//            AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void): Void? {
//                noteDao?.insertNote(NoteEntity("Title 1", "sekrnvwaekbjgkwauebgkwabeg", false))
//                noteDao?.insertNote(NoteEntity("Title 2", "sekrnvwaekbjgkwauebgkwabeg", false))
//                noteDao?.insertNote(NoteEntity("Title 3", "sekrnvwaekbjgkwauebgkwabeg", false))
//                return null
//            }
//        }

    }

    abstract fun noteDao(): NoteDao
}