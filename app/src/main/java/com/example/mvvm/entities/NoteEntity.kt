package com.example.mvvm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_note")
public class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var title: String,

    var description: String,

    var isDone: Boolean

    /*,

    var priority: Int,

    var something: String*/

) {

}