package com.example.rickmortyapi.utils.retrofit

import com.example.rickmortyapi.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("api/character")
    fun getCharacters(): Call<CharacterResponse>

    @GET("api/character/")
    fun getCharactersUpdate(@Query("page") page: Int?): Call<CharacterResponse>
}