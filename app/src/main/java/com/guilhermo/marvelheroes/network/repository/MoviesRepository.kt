package com.guilhermo.marvelheroes.network.repository

import android.util.Log
import com.guilhermo.marvelheroes.network.MarvelApi
import com.guilhermo.marvelheroes.network.model.character.CharacterResponse
import com.guilhermo.marvelheroes.network.model.characters.CharactersResponse
import com.guilhermo.marvelheroes.network.model.comics.ComicsResponse

class HeroesRepository(private val api: MarvelApi) : IHeroesRepository {

    override suspend fun getHeroes(): CharactersResponse? {
        return try {
            api.getCharacters()
        } catch (e: Exception) {
            Log.e(HeroesRepository::class.java.name, e.toString())
            null
        }
    }

    override suspend fun getHeroDetails(heroId: String): CharacterResponse? {
        return try {
            api.getCharacter(heroId)
        } catch (e: Exception) {
            Log.e(HeroesRepository::class.java.name, e.toString())
            null
        }
    }

    override suspend fun getHeroesByName(name: String): CharactersResponse? {
        return try {
            api.getCharacters(name)
        } catch (e: Exception) {
            Log.e(HeroesRepository::class.java.name, e.toString())
            null
        }
    }

    override suspend fun getHeroesComics(heroId: String): ComicsResponse? {
        return try {
            api.getComicsForCharacter(heroId)
        } catch (e: Exception) {
            Log.e(HeroesRepository::class.java.name, e.toString())
            null
        }
    }

}