package com.guilhermo.marvelheroes.network

import com.guilhermo.marvelheroes.network.model.character.CharacterResponse
import com.guilhermo.marvelheroes.network.model.characters.CharactersResponse
import com.guilhermo.marvelheroes.network.model.comics.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("name") name: String? = null,
        @Query("limit") limit: String = "100"
    ): CharactersResponse

    @GET("characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: String): CharacterResponse

    @GET("characters/{characterId}/comics")
    suspend fun getComicsForCharacter(@Path("characterId") characterId: String): ComicsResponse

}