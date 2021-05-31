package com.example.rickmortyapi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickmortyapi.utils.retrofit.APIClient
import com.example.rickmortyapi.models.CharacterResponse
import com.example.rickmortyapi.utils.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharactersRepository {

    var apiInterface: APIInterface? = null
    private val characterResponse = MutableLiveData<CharacterResponse>()

    fun getCharacters(): MutableLiveData<CharacterResponse> {

//        SessionData.isLoading = true
        apiInterface = APIClient.getClient()?.create(APIInterface::class.java)
        val call = apiInterface!!.getCharacters()
        call.enqueue(object : Callback<CharacterResponse> {
            @Override
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                val response = response.body()
                response?.let {
                    characterResponse.value = it
                }
//                for (i in randomUserResponse.value!!.results) {
//                    SessionData.users.add(i)
//                }
            }

            @Override
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("ERROR", t.toString());
            }
        })

        return characterResponse
    }
}