package com.jesus.moviesskell.data.repository

import MoviesPagerSource
import com.jesus.moviesskell.network.services.MoviesApiService
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.domain.Result

class MoviesRepositoryImpl(private val movieService: MoviesApiService, private val moviesPagerSource: MoviesPagerSource): MoviesRepository {
    override suspend fun getMovies(page: Int): Result<List<MovieData>> = try {
        val result = this.movieService.getMovies(page).results
        if (result.isEmpty()) Result.Empty()
        else Result.Success(result)
    } catch (e: Exception) {
        Result.Failure(e.message ?: e.localizedMessage)
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieData> = try {
        val result = this.movieService.getMovieDetail(id)
        Result.Success(result)
    } catch (e: Exception) {
        Result.Failure(e.message ?: e.localizedMessage)
    }

    override fun getMoviesPagerSource(): MoviesPagerSource {
       return this.moviesPagerSource
    }
}