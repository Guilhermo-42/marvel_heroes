package com.guilhermo.marvelheroes.network.model.character


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("status")
    val status: String?
)