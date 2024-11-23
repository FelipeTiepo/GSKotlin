package com.example.gskotlin.viewmodel


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SugestoesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SugestoesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SugestoesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}