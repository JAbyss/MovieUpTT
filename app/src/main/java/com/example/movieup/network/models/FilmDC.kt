package com.example.movieup.network.models

data class FilmDC(
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: String,
    val byline: String,
    val headline: String,
    val summary_short: String?,
    val publication_date: String?,
    val opening_date: String?,
    val date_updated: String?,
    val link: LinkDC,
    val multimedia: MultimediaDC,
    )