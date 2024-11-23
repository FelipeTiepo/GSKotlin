package com.example.gskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.gskotlin.data.SugestaoDao
import com.example.gskotlin.data.SugestaoDataBase
import com.example.gskotlin.model.SugestaoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SugestoesViewModel(application: Application) : AndroidViewModel(application) {

    private val sugestaoDao: SugestaoDao
    val sugestoesLiveData: LiveData<List<SugestaoModel>>

    init {

        val database = Room.databaseBuilder(
            getApplication(),
            SugestaoDataBase::class.java,
            "sugestoes_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        sugestaoDao = database.sugestaoDao()
        sugestoesLiveData = sugestaoDao.getAll()
    }


    fun addSugestao(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newSugestao = SugestaoModel(title = title, description = description)
            sugestaoDao.insert(newSugestao)
        }
    }


    fun removeSugestao(sugestao: SugestaoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            sugestaoDao.delete(sugestao)
        }
    }
}