package com.guilhermo.marvelheroes.flow.heroes.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermo.marvelheroes.network.repository.HeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroDetailsViewModel(private val repository: HeroesRepository) : ViewModel() {

    private val mutablePresenter: MutableLiveData<HeroDetailsPresenter> by lazy {
        MutableLiveData<HeroDetailsPresenter>()
    }

    val livePresenter: LiveData<HeroDetailsPresenter> get() = mutablePresenter

    private val mutableComicsPresenter: MutableLiveData<HeroComicsPresenter> by lazy {
        MutableLiveData<HeroComicsPresenter>()
    }

    val liveComicsPresenter: LiveData<HeroComicsPresenter> get() = mutableComicsPresenter

    fun retrieveHeroDetails(heroId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val heroDetailsResponse = repository.getHeroDetails(heroId)

            val hero = heroDetailsResponse?.data?.results?.firstOrNull()

            if (heroDetailsResponse == null || hero == null) {
                withContext(Dispatchers.Main) {
                    mutablePresenter.value = HeroDetailsPresenter()
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                mutablePresenter.value =
                    HeroDetailsPresenter.fromHero(hero)
            }

            hero.id?.let { retrieveComics(it) }

        }
    }

    private suspend fun retrieveComics(heroId: String) {
        val comicsResponse = repository.getHeroesComics(heroId)

        if (comicsResponse == null || comicsResponse.data?.results.isNullOrEmpty()) {
            withContext(Dispatchers.Main) {
                mutableComicsPresenter.value = HeroComicsPresenter()
            }
            return
        }

        withContext(Dispatchers.Main) {
            mutableComicsPresenter.value = HeroComicsPresenter.fromModel(
                comicsResponse.data?.results ?: listOf()
            )
        }

    }

}