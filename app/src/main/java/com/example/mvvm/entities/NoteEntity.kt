package com.example.mvvm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_note")
public class NoteEntity(

    var title: String,

    var description: String,

    var isDone: Boolean

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}