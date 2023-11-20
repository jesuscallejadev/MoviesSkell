package com.jesus.moviesskell.data.repository

import MoviesPagerSource
import android.util.Log
import com.jesus.moviesskell.network.services.MoviesApiService
import com.jesus.moviesskell.data.response.toDomainModel
import com.jesus.moviesskell.domain.models.result.Result
import com.jesus.moviesskell.domain.models.movies.Movie
import java.io.IOException

private const val TAG = "MoviesRepositoryImpl"
class MoviesRepositoryImpl(
    private val movieService: MoviesApiService,
    private val moviesPagerSource: MoviesPagerSource
) : MoviesRepository {
    override suspend fun getMovies(page: Int): Result<List<Movie>> = try {
        val result = movieService.getMovies(page).results.map { it.toDomainModel() }
        when {
            result.isEmpty() -> Result.Empty()
            else -> Result.Success(result)
        }
    } catch (e: IOException) {
        Log.i(TAG, "Network Error")
        Result.Failure("Network Error: ${e.message}")
    } catch (e: Exception) {
        Log.i(TAG, "Unknown Error")
        Result.Failure("Unknown Error: ${e.message ?: e.localizedMessage}")
    }

    override suspend fun getMovieDetail(id: Int): Result<Movie> = try {
        val result = movieService.getMovieDetail(id).toDomainModel()
        Result.Success(result)
    } catch (e: IOException) {
        Log.i(TAG, "Network Error")
        Result.Failure("Network Error: ${e.message}")
    } catch (e: Exception) {
        Log.i(TAG, "Unknown Error")
        Result.Failure("Unknown Error: ${e.message ?: e.localizedMessage}")
    }

    override fun getMoviesPagerSource(): MoviesPagerSource {
        return this.moviesPagerSource
    }
}