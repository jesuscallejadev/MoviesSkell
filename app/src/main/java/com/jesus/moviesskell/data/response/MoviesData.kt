package com.jesus.moviesskell.data.response
import com.google.gson.annotations.SerializedName

data class MoviesData(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieData>
)