package com.jesus.moviesskell.features.movies.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.jesus.moviesskell.R
import com.jesus.moviesskell.databinding.FragmentMoviesBinding
import com.jesus.moviesskell.databinding.FragmentOnboardingBinding
import com.jesus.moviesskell.features.movies.view.adapter.MovieItemClickListener
import com.jesus.moviesskell.features.movies.view.adapter.MoviesPagerAdapter
import com.jesus.moviesskell.features.movies.viewModel.MoviesViewModel
import com.jesus.moviesskell.paging.LoaderAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MoviesFragment"

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesPagerAdapter: MoviesPagerAdapter
    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        this.setUpView(binding = binding)
        this.loadMovies()
        return binding.root
    }

    private fun setUpView(binding: FragmentMoviesBinding) {
        moviesPagerAdapter = MoviesPagerAdapter(movieItemClickListener = MovieItemClickListener {
            this.onMovieItemTap(movieId = it)
        })

        binding.moviesRv.apply {
            this.adapter = moviesPagerAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(), footer = LoaderAdapter()
            )
        }

        moviesPagerAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.NotLoading -> {
                    if (loadState.source.refresh is LoadState.NotLoading) {
                        binding.moviesRv.visibility = View.VISIBLE
                        if (moviesPagerAdapter.itemCount == 0) {
                            Log.i(TAG, "Empty list")
                        } else {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }

                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.moviesRv.visibility = View.GONE
                }

                is LoadState.Error -> {
                    Toast.makeText(context, "Error loading movies", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadMovies() {
        lifecycleScope.launch {
            viewModel.moviePager.collectLatest { pagingData ->
                moviesPagerAdapter.submitData(pagingData)
            }
        }
    }

    private fun onMovieItemTap(movieId: Int) {
        this.findNavController()
            .navigate(MoviesFragmentDirections.goToMovieDetail(movieId = movieId))
    }
}
