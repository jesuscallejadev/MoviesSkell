package com.jesus.moviesskell.features.movieDetail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesus.moviesskell.data.repository.MoviesRepository
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MoviesRepository): ViewModel() {

    private val _movieStatus = MutableLiveData<Result<MovieData>>()
    val movieStatus: LiveData<Result<MovieData>> = _movieStatus

    fun getMovieDetail(movieId: Int) {
        _movieStatus.postValue(Result.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            _movieStatus.postValue(movieRepository.getMovieDetail(id = movieId))
        }
    }
}