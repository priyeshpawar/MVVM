package com.example.mvvm.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm.entities.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM tb_note")
    fun deleteAllNote()

    @Query("SELECT * FROM tb_note")
    fun getAllNotes(): LiveData<List<NoteEntity>>
}