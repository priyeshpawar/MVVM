package com.example.mvvm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_note")
public class NoteEntity(

    private var title: String,

    private var description: String,

    private var isDone: Boolean

) {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0
}