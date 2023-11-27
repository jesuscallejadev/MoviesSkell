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
    val backdropPath: String? = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "en",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
) {
    companion object {
        fun createTestInstance(
            id: Int = 1,
            title: String = "Test Movie",
            overview: String = "This is a test movie.",
            releaseDate: String = "2022-01-01",
            posterPath: String = "/test_poster.jpg",
            backdropPath: String = "/test_backdrop.jpg",
            runtime: Int = 120,
            originalLanguage: String = "en",
            voteAverage: Double = 8.0
        ): MovieData {
            return MovieData(
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
    }
}

//DTO
fun MovieData.toDomainModel(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = checkBackdropImage(backdropPath),
        runtime = runtime,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage
    )
}

fun checkBackdropImage(image: String?): String {
    return image ?: "harcooded"
}