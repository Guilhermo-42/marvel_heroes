package com.guilhermo.marvelheroes.flow.heroes.details

import com.guilhermo.marvelheroes.network.model.character.Result
import java.io.Serializable

data class HeroDetailsPresenter(

    val heroPosterPath: String? = null,
    val heroName: String = "",
    val heroDescription: String = ""

) : Serializable {
    companion object {
        fun fromHero(hero: Result): HeroDetailsPresenter {
            return HeroDetailsPresenter(
                hero.thumbnail?.path?.plus("/standard_large").plus("." + hero.thumbnail?.extension),
                hero.name.takeIf {
                    it?.isBlank() == false
                } ?: "No Name Found",
                hero.description.takeIf {
                    it?.isBlank() == false
                } ?: "No Description Found"
            )
        }

    }

}