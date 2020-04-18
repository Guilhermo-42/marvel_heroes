package com.guilhermo.marvelheroes.flow.heroes

import com.guilhermo.marvelheroes.network.model.characters.Result
import java.io.Serializable

data class HeroListPresenter(

    val heroes: List<HeroListItemPresenter> = emptyList()

) : Serializable {
    class HeroListItemPresenter(
        val heroId: String = "",
        val heroName: String = "",
        val heroImagePath: String? = null,
        val heroDescription: String = ""
    ) : Serializable {

        companion object {
            fun fromHero(hero: Result): HeroListItemPresenter {
                return HeroListItemPresenter(
                    hero.id.toString(),
                    hero.name.takeIf {
                        it?.isBlank() == false
                    } ?: "No Name Found",
                    hero.thumbnail?.path?.plus("/standard_large").plus("." + hero.thumbnail?.extension),
                    hero.description.takeIf {
                        it?.isBlank() == false
                    } ?: "No Description Found"
                )
            }
        }

    }
}