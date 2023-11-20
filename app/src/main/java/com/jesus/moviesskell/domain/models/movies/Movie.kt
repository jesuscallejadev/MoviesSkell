package com.jesus.moviesskell.domain.models.movies

data class Movie(
    val id: Int,
    val title: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val runtime: Int = 0,
    val originalLanguage: String = "en",
    val voteAverage: Double = 0.0
)
