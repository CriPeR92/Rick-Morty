package com.example.rickmortyapi.models

import com.google.gson.annotations.SerializedName


data class CharacterResponse(@SerializedName("results") var results : ArrayList<Personage>,
                             @SerializedName("info") var info : Info)

class Info( @SerializedName("count") var count : Int?,
            @SerializedName("pages") var pages : Int?,
            @SerializedName("next") var next : String?,
            @SerializedName("prev") var prev : String?)

class Personage(@SerializedName("id") var id : Int?,
                @SerializedName("name") var name : String?,
                @SerializedName("status") var status : String?,
                @SerializedName("species") var species : String?,
                @SerializedName("type") var type : Any?,
                @SerializedName("gender") var gender : String?,
                @SerializedName("origin") var origin : Any?,
                @SerializedName("location") var location : Any?,
                @SerializedName("image") var image : String?,
                @SerializedName("episode") var episode : Any?,
                @SerializedName("url") var url : String?,
                @SerializedName("created") var created : String?)