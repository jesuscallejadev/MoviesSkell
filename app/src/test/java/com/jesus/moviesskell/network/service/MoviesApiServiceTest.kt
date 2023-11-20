package com.jesus.moviesskell.network.service

import com.google.common.truth.Truth.assertThat
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.data.response.MoviesData
import com.jesus.moviesskell.network.services.MoviesApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MoviesApiServiceTest {

    @Mock
    lateinit var moviesApiService: MoviesApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getMovies should return MoviesData`() = runBlocking {
        val fakePage = 1
        val fakeMoviesData = MoviesData.createTestInstance()
        `when`(moviesApiService.getMovies(fakePage)).thenReturn(fakeMoviesData)

        val result = moviesApiService.getMovies(fakePage)

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(fakeMoviesData)
    }

    @Test
    fun `getMovieDetail should return MovieData`() = runBlocking {
        val fakeMovieId = 123
        val fakeMovieData = MovieData.createTestInstance()
        `when`(moviesApiService.getMovieDetail(fakeMovieId)).thenReturn(fakeMovieData)

        val result = moviesApiService.getMovieDetail(fakeMovieId)

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(fakeMovieData)
    }
}
