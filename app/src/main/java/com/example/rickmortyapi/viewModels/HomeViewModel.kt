package com.example.rickmortyapi.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortyapi.models.CharacterResponse
import com.example.rickmortyapi.models.Personage
import com.example.rickmortyapi.repository.CharactersRepository

class HomeViewModel : ViewModel() {

    var characterResponse = MutableLiveData<CharacterResponse>()
    var character = MutableLiveData<Personage>()
    val uiEventValue = MutableLiveData<Int>()

    private val _hide = MutableLiveData(1)
    val hide: MutableLiveData<Int> = _hide

    init {
        val userRepository: CharactersRepository by lazy {
            CharactersRepository
        }
        characterResponse = userRepository.getCharacters()
    }

    fun onClickActionGridAdapter(selectedCharacter: Personage, type: Int) {
        character.value = selectedCharacter
        onActionViewModel(type)
    }

    private fun onActionViewModel(type: Int) {
        uiEventValue.value = type
    }

}