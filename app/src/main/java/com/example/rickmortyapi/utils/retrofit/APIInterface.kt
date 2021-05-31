package com.example.rickmortyapi.utils.retrofit

import com.example.rickmortyapi.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("character/")
    fun getCharacters(): Call<CharacterResponse>
}