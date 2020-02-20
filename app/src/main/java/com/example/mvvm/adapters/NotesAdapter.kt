package com.example.mvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.entities.NoteEntity

class NotesAdapter(private val context: Context, private val notesList: List<NoteEntity>) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.list_row_notes, parent, false)
        view.setOnClickListener(this)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val position: Int = holder.adapterPosition
        val note: NoteEntity = notesList[position]

        holder.tvTitle.text = note.title

        holder.tvDescription.text = note.description

        if (note.isDone) holder.cvMainLayout.setBackgroundColor(context.resources.getColor(R.color.greenLight))
        else holder.cvMainLayout.setBackgroundColor(context.resources.getColor(R.color.redLight))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvMainLayout: CardView = itemView.findViewById(R.id.cv_main_layout)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onClick(v: View?) {
        Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
    }

}