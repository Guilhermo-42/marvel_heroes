package com.guilhermo.marvelheroes

import android.app.Application
import com.guilhermo.marvelheroes.di.networkModule
import com.guilhermo.marvelheroes.di.repositoryModule
import com.guilhermo.marvelheroes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}