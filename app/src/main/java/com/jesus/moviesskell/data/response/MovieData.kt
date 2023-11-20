package com.jesus.moviesskell.data.response

import com.google.gson.annotations.SerializedName
import com.jesus.moviesskell.domain.models.movies.Movie

data class MovieData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("overview")
    val overview: String =
        "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "en",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
)

//DTO
fun MovieData.toDomainModel(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        runtime = runtime,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage
    )
}
