package com.example.rickmortyapi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortyapi.models.Personage

class CharacterViewModel(character: Personage) : ViewModel() {

    private val _name = MutableLiveData(character.name)
    private val _status = MutableLiveData(character.status)
    private val _species =  MutableLiveData(character.species)
    private val _gender = MutableLiveData(character.gender)
    private val _image = MutableLiveData(character.image)
    private val _url = MutableLiveData(character.url)

    val name: LiveData<String?> = _name
    val status: LiveData<String?> = _status
    val species: LiveData<String?> = _species
    val gender: LiveData<String?> = _gender
    val image: LiveData<String?> = _image
    val url: LiveData<String?> = _url
    
}