package com.guilhermo.marvelheroes.flow.heroes

import androidx.lifecycle.*
import com.guilhermo.marvelheroes.network.repository.HeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroListViewModel(private val repository: HeroesRepository) : ViewModel(),
    LifecycleObserver {

    private val mutableHeroesPresenter: MutableLiveData<HeroListPresenter> by lazy {
        MutableLiveData<HeroListPresenter>()
    }

    val liveHeroesPresenter: LiveData<HeroListPresenter> get() = mutableHeroesPresenter

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun retrieveHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            val heroesResponse = repository.getHeroes()

            if (heroesResponse?.data == null || heroesResponse.data.results.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    mutableHeroesPresenter.value = HeroListPresenter()
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                mutableHeroesPresenter.value = HeroListPresenter(
                    heroesResponse.data.results.map { hero ->
                        hero.let {
                            HeroListPresenter.HeroListItemPresenter.fromHero(it)
                        }
                    }
                )
            }

        }
    }

    fun onSearchClicked(newText: String) {
        if (newText.isBlank()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val heroesResponse = repository.getHeroesByName(newText)

            if (heroesResponse == null || heroesResponse.data?.results.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    mutableHeroesPresenter.value = HeroListPresenter()
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                mutableHeroesPresenter.value = HeroListPresenter(
                    heroesResponse.data?.results?.map { hero ->
                        HeroListPresenter.HeroListItemPresenter.fromHero(hero)
                    } ?: listOf()
                )
            }
        }
    }

}