package com.example.rickmortyapi.models

class SessionData {

    companion object {
        val characters : MutableList<Personage> = ArrayList()
        var page = 1
        var isLoading = false
        var pastVisibleItems = 0
//
//        var userFragment: User? = null
//        var userFragmentSaved: UserData? = null
    }
}