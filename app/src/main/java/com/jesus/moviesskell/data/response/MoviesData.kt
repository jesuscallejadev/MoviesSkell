package com.jesus.moviesskell.data.response
import com.google.gson.annotations.SerializedName

data class MoviesData(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieData>
) {
    companion object {
        fun createTestInstance(
            totalPages: Int = 1,
            results: List<MovieData> = listOf(
                MovieData.createTestInstance(id = 1, title = "Test Movie 1"),
                MovieData.createTestInstance(id = 2, title = "Test Movie 2")
            )
        ): MoviesData {
            return MoviesData(
                totalPages = totalPages,
                results = results
            )
        }
    }
}
