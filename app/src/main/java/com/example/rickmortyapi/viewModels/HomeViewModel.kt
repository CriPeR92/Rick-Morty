package com.example.rickmortyapi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortyapi.models.CharacterResponse
import com.example.rickmortyapi.models.Personage
import com.example.rickmortyapi.repository.CharactersRepository

class HomeViewModel : ViewModel() {
    var characterResponse = MutableLiveData<CharacterResponse>()

    var character : LiveData<CharacterResponse> = characterResponse
    val uiEventValue = MutableLiveData<Int>()

    init {
        val userRepository: CharactersRepository by lazy {
            CharactersRepository
        }
        characterResponse = userRepository.getCharacters()
    }

    fun onClickActionGridAdapter(character: Personage, type: Int) {
//        SessionData.userFragment = character
        onActionViewModel(type)
    }

//    fun onClickActionSavedUserAdapter(user: UserData) {
//        SessionData.userFragmentSaved = user
//        onActionViewModel(2)
//    }

    private fun onActionViewModel(type: Int) {
        uiEventValue.value = type
    }

}