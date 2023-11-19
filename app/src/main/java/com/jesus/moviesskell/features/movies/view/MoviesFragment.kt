package com.jesus.moviesskell.features.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.jesus.moviesskell.R
import com.jesus.moviesskell.databinding.FragmentMoviesBinding
import com.jesus.moviesskell.features.movies.view.adapter.MoviesPagerAdapter
import com.jesus.moviesskell.features.movies.viewModel.MoviesViewModel
import com.jesus.moviesskell.paging.LoaderAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesPagerAdapter: MoviesPagerAdapter
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        moviesPagerAdapter = MoviesPagerAdapter()

        binding.moviesRv.apply {
            this.adapter = moviesPagerAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }
        this.loadMovies()
        return binding.root
    }

    private fun loadMovies() {
        lifecycleScope.launch {
            viewModel.moviePager.collectLatest { pagingData ->
                moviesPagerAdapter.submitData(pagingData)
            }
        }
    }
}


//TODO: ON DETAIL NAV
/*
private fun onItemClick(movie: Movie) {
val action = MovieFragmentDirections.toMovieDetailFragment(movie.id)
Navigation.findNavController(requireView()).navigate(action)
}**/
