package com.example.rickmortyapi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyapi.models.Personage

class ViewModelFactory(private val personage: Personage) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(personage) as T
    }
}