package com.jesus.moviesskell.features.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jesus.moviesskell.data.repository.MoviesRepository
import com.jesus.moviesskell.data.response.MovieData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    val moviePager: StateFlow<PagingData<MovieData>> =
        Pager(
            PagingConfig(pageSize = 1)
        ) {
            moviesRepository.getMoviesPagerSource()
        }.flow
//            .map {pagingData ->
//                pagingData
//                    .map {movie -> movie }
//                    }
//            }
            .cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}