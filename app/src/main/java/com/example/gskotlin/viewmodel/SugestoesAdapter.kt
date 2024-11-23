package com.example.gskotlin.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gskotlin.R
import com.example.gskotlin.model.SugestaoModel

class SugestoesAdapter(private val onSugestaoRemoved: (SugestaoModel) -> Unit) :
    RecyclerView.Adapter<SugestoesAdapter.SugestaoViewHolder>() {

    private var sugestoes = listOf<SugestaoModel>()
    private var filteredSugestoes = listOf<SugestaoModel>()

    inner class SugestaoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
        val button: ImageButton = view.findViewById(R.id.imageButton)

        fun bind(sugestao: SugestaoModel) {
            textViewTitle.text = sugestao.title
            textViewDescription.text = sugestao.description
            button.setOnClickListener {
                onSugestaoRemoved(sugestao)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugestaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sugestao_layout, parent, false)
        return SugestaoViewHolder(view)
    }

    override fun getItemCount(): Int = filteredSugestoes.size

    override fun onBindViewHolder(holder: SugestaoViewHolder, position: Int) {
        val sugestao = filteredSugestoes[position]
        holder.bind(sugestao)
    }

    fun updateSugestoes(newSugestoes: List<SugestaoModel>) {
        sugestoes = newSugestoes
        filteredSugestoes = newSugestoes
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredSugestoes = if (query.isEmpty()) {
            sugestoes
        } else {
            sugestoes.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}