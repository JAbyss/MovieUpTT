package com.example.movieup.network.api

import com.example.movieup.BuildConfig
import com.example.movieup.network.models.ResponseFilms
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {

    @GET("./svc/movies/v2/reviews/all.json")
    suspend fun getAllFilms(
        @Query("api-key") api_key: String = BuildConfig.SERVER_API
    ): Response<ResponseFilms>

    @GET("./svc/movies/v2/reviews/all.json")
    suspend fun getOffsetFilms(
        @Query("api-key") api_key: String = BuildConfig.SERVER_API,
        @Query("offset") offset: Int
    ): Response<ResponseFilms>

}