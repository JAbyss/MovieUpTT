package com.example.movieup.network.models

data class ResponseFilms(
    val status: String,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<FilmDC>
    )