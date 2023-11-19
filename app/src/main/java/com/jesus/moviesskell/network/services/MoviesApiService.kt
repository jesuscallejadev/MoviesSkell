package com.jesus.moviesskell.network.services

import com.jesus.moviesskell.data.response.MoviesData
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int): MoviesData
}