package com.jesus.moviesskell.data.response

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("overview")
    val overview: String =
        "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "en",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
)