package com.guilhermo.marvelheroes.network.model.comics


import com.google.gson.annotations.SerializedName

data class ComicsResponse(
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