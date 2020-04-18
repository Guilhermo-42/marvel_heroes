package com.guilhermo.marvelheroes.di

import com.guilhermo.marvelheroes.BuildConfig
import com.guilhermo.marvelheroes.network.MarvelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<HttpLoggingInterceptor>())
            addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url;

                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .addQueryParameter("ts", BuildConfig.TS)
                    .addQueryParameter("hash", BuildConfig.HASH)
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(MarvelApi::class.java) }
}