package com.example.movieup.network

import com.example.movieup.network.api.FilmApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetrofitModule @Inject constructor() {

    @Singleton
    @Provides
    fun filmRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()


    @Singleton
    @Provides
    fun provideFilmApi(retrofit: Retrofit): FilmApi =
        retrofit.create(FilmApi::class.java)

}