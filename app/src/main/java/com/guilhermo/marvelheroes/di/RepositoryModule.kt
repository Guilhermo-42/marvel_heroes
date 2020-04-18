package com.guilhermo.marvelheroes.di

import com.guilhermo.marvelheroes.network.repository.HeroesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HeroesRepository(get()) }
}