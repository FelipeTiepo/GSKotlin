package com.example.gskotlin

import com.example.gskotlin.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gskotlin.viewmodel.SugestoesAdapter
import com.example.gskotlin.viewmodel.SugestoesViewModel
import com.example.gskotlin.viewmodel.SugestoesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SugestoesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = SugestoesViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SugestoesViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sugestões Ecológicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val sugestoesAdapter = SugestoesAdapter { sugestao ->
            viewModel.removeSugestao(sugestao)
        }
        recyclerView.adapter = sugestoesAdapter

        viewModel.sugestoesLiveData.observe(this) { sugestoes ->
            sugestoesAdapter.updateSugestoes(sugestoes)
        }

        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    sugestoesAdapter.filter(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    sugestoesAdapter.filter(it)
                }
                return true
            }
        })

        val button = findViewById<Button>(R.id.button)
        val editTextTitle = findViewById<EditText>(R.id.editText)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)

        button.setOnClickListener {
            if (editTextTitle.text.isEmpty()) {
                editTextTitle.error = "Insira o título da sugestão"
                return@setOnClickListener
            }
            if (editTextDescription.text.isEmpty()) {
                editTextDescription.error = "Insira a descrição da sugestão"
                return@setOnClickListener
            }

            viewModel.addSugestao(editTextTitle.text.toString(), editTextDescription.text.toString())
            editTextTitle.text.clear()
            editTextDescription.text.clear()
        }
    }
}