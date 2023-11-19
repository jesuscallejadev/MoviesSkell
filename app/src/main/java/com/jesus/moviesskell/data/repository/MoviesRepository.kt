package com.jesus.moviesskell.data.repository

import MoviesPagerSource
import com.jesus.moviesskell.domain.Result
import com.jesus.moviesskell.data.response.MovieData

interface MoviesRepository {
    suspend fun getMovies(page: Int = 1): Result<List<MovieData>>
    fun getMoviesPagerSource(): MoviesPagerSource
}