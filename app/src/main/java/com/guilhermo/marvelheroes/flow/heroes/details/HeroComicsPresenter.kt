package com.guilhermo.marvelheroes.flow.heroes.details

import com.guilhermo.marvelheroes.network.model.comics.Result
import java.io.Serializable

data class HeroComicsPresenter(

    val comics: List<ComicPresenter> = listOf()

) : Serializable {

    companion object {
        fun fromModel(comics: List<Result>): HeroComicsPresenter {
            return HeroComicsPresenter(
                comics.map {
                    ComicPresenter.fromModel(it)
                }
            )
        }
    }

    class ComicPresenter(
        val imageUrl: String? = null,
        val name: String? = null
    ) {
        companion object {
            fun fromModel(comic: Result): ComicPresenter {
                return ComicPresenter(
                    comic.thumbnail?.path?.plus("/standard_large").plus("." + comic.thumbnail?.extension),
                    comic.title.takeIf {
                        it?.isBlank() == false
                    } ?: "No Title Found"
                )
            }
        }

    }

}