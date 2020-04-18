package com.guilhermo.marvelheroes.network.repository

import com.guilhermo.marvelheroes.network.model.character.CharacterResponse
import com.guilhermo.marvelheroes.network.model.characters.CharactersResponse
import com.guilhermo.marvelheroes.network.model.comics.ComicsResponse

interface IHeroesRepository {

    /**
     * Returns a List heroes.
     *
     * @return [CharactersResponse] object.
     **/
    suspend fun getHeroes(): CharactersResponse?

    /**
     * Returns details of a given hero given its id.
     *
     * @param heroId the hero id.
     * @return [CharacterResponse] object.
     **/
    suspend fun getHeroDetails(heroId: String): CharacterResponse?

    /**
     * Returns a list of heroes given a name.
     *
     * @param name the given query name for search.
     * @return [CharactersResponse] object.
     **/
    suspend fun getHeroesByName(name: String): CharactersResponse?

    /**
     * Returns a list of comics.
     *
     * @param heroId the given hero id.
     * @return [ComicsResponse] object.
     */
    suspend fun getHeroesComics(heroId: String): ComicsResponse?

}