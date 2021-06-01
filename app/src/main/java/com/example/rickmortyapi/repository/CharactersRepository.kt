package com.example.rickmortyapi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapi.utils.retrofit.APIClient
import com.example.rickmortyapi.models.CharacterResponse
import com.example.rickmortyapi.models.SessionData
import com.example.rickmortyapi.utils.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharactersRepository {

    var apiInterface: APIInterface? = null
    private val characterResponse = MutableLiveData<CharacterResponse>()
    private val characterResponseUpdate = MutableLiveData<CharacterResponse>()


    fun getCharacters(): MutableLiveData<CharacterResponse> {

        SessionData.isLoading = true
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
                for (i in characterResponse.value!!.results) {
                    SessionData.characters.add(i)
                }
            }

            @Override
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("ERROR", t.toString());
            }
        })

        return characterResponse
    }

    fun getCharactersUpdate(param: RecyclerView): MutableLiveData<CharacterResponse> {

        SessionData.isLoading = true
        apiInterface = APIClient.getClient()?.create(APIInterface::class.java)
        val call = apiInterface!!.getCharactersUpdate(page = SessionData.page)
        call.enqueue(object : Callback<CharacterResponse> {
            @Override
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                val usersResponse = response.body()
                usersResponse?.let {
                    characterResponse.value = it as CharacterResponse

                    characterResponse.value = response.body()
                }
                for (i in characterResponse.value!!.results) {
                    SessionData.characters.add(i)
                }
                param.adapter?.notifyDataSetChanged()
            }

            @Override
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("ERROR", t.toString());
            }
        })
        return characterResponse
    }
}