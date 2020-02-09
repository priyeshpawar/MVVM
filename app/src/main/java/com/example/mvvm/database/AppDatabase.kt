package com.example.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.daos.NoteDao
import com.example.mvvm.entities.NoteEntity

@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            var db: AppDatabase? = dbInstance

            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "mvvm_database"
                ).build();
            }
            return db;
        }
    }

    abstract fun noteDao(): NoteDao


}