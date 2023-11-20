package com.jesus.moviesskell.data.repository

import MoviesPagerSource
import com.jesus.moviesskell.domain.models.result.Result
import com.jesus.moviesskell.domain.models.movies.Movie

interface MoviesRepository {
    suspend fun getMovies(page: Int = 1): Result<List<Movie>>
    suspend fun getMovieDetail(id: Int): Result<Movie>
    fun getMoviesPagerSource(): MoviesPagerSource
}