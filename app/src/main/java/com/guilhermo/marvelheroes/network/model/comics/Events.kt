package com.guilhermo.marvelheroes.network.model.comics


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    val available: String?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<ItemXX>?,
    @SerializedName("returned")
    val returned: String?
)