package com.example.mvvm.viewmodels

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm.R
import com.example.mvvm.entities.NoteEntity
import com.example.mvvm.repositories.NoteRepository

class NotesListViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository

    init {
        noteRepository = NoteRepository(application)
    }

    private fun insert(note: NoteEntity) {
        noteRepository.insert(note)
    }

    private fun update(note: NoteEntity) {
        noteRepository.update(note)
    }

    private fun delete(note: NoteEntity?) {
        noteRepository.delete(note)
    }

    fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return noteRepository.getAllNotes()
    }

    fun getCount(): Int {
        return noteRepository.getCount()
    }

    fun showAddNoteDialog(
        context: Context,
        note: NoteEntity?
    ) {
        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.dialog_add_note, null)

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)

        if (note == null) alertDialogBuilder.setTitle("Add Note")
        else alertDialogBuilder.setTitle("Edit Note")

        val edtTitle: EditText = view.findViewById(R.id.edt_title)
        val edtDescription: EditText = view.findViewById(R.id.edt_description)
        val cbIsCompleted: CheckBox = view.findViewById(R.id.cb_is_completed)
        val btnSave: Button = view.findViewById(R.id.btn_save)
        val btnEdit: Button = view.findViewById(R.id.btn_edit)
        val btnDelete: Button = view.findViewById(R.id.btn_delete)


        if (note == null) {
            btnEdit.visibility = View.GONE
            btnDelete.visibility = View.GONE
        } else {
            btnSave.visibility = View.GONE
            edtTitle.setText(note.title)
            edtDescription.setText(note.description)
            cbIsCompleted.setChecked(note.isDone)
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()

        btnSave.setOnClickListener {
            if (edtTitle.text.toString().trim().isEmpty()) {
                edtTitle.error = "Please enter title"
                return@setOnClickListener
            }

            if (edtDescription.text.toString().trim().isEmpty()) {
                edtDescription.error = "Please enter description"
                return@setOnClickListener
            }

            insert(
                NoteEntity(
                    getCount(),
                    edtTitle.text.toString().trim(),
                    edtDescription.text.toString().trim(),
                    cbIsCompleted.isChecked
                )
            )

            alertDialog.dismiss()
        }

        btnEdit.setOnClickListener {
            if (edtTitle.text.toString().trim().isEmpty()) {
                edtTitle.error = "Please enter title"
                return@setOnClickListener
            }

            if (edtDescription.text.toString().trim().isEmpty()) {
                edtDescription.error = "Please enter description"
                return@setOnClickListener
            }


            val id: Int = note?.id ?: 0
            update(
                NoteEntity(
                    id,
                    edtTitle.text.toString().trim(),
                    edtDescription.text.toString().trim(),
                    cbIsCompleted.isChecked
                )
            )

            alertDialog.dismiss()
        }

        btnDelete.setOnClickListener {
            delete(note)

            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}