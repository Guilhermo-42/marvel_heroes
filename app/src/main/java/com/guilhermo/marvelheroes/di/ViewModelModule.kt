package com.guilhermo.marvelheroes.di

import com.guilhermo.marvelheroes.flow.heroes.HeroListViewModel
import com.guilhermo.marvelheroes.flow.heroes.details.HeroDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HeroListViewModel(get()) }
    viewModel { HeroDetailsViewModel(get()) }
}