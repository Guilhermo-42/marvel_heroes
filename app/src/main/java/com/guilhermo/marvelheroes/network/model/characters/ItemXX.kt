package com.guilhermo.marvelheroes.network.model.characters


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)