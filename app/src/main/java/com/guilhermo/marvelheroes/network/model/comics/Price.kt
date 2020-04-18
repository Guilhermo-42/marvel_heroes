package com.guilhermo.marvelheroes.network.model.comics


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: String?,
    @SerializedName("type")
    val type: String?
)