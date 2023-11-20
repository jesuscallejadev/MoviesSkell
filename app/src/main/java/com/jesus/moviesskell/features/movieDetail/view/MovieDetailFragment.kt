package com.jesus.moviesskell.features.movieDetail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesus.moviesskell.R
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.databinding.FragmentMovieDetailBinding
import com.jesus.moviesskell.domain.models.movies.Movie
import com.jesus.moviesskell.features.movieDetail.viewModel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jesus.moviesskell.domain.models.result.Result
import com.jesus.moviesskell.network.Api
import kotlinx.coroutines.launch

private const val TAG = "MovieDetailFragment"
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private lateinit var binding: FragmentMovieDetailBinding
    private var movieId: Int = 0
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        this.movieId = args.movieId

        binding.backArrowImage.setOnClickListener {
            this.findNavController().popBackStack()
        }

        this.onEventChange()
        this.getMovieDetail(movieId = this.movieId)
        return binding.root
    }

    private fun getMovieDetail(movieId: Int) {
        lifecycleScope.launch {
            viewModel.getMovieDetail(movieId = movieId)
        }
    }

    private fun onEventChange() {
        viewModel.movieStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    this.setMovieDetails(movie = it.data)
                }

                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                }

                else -> {
                    Log.i(TAG, "Unexpected result state")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieDetails(movie: Movie?) {
        val movieImageURL = Api.IMAGES_BASE_URL + movie?.backdropPath
        binding.apply {
            val movieImageUri = movieImageURL.toUri().buildUpon().scheme("https").build()
            Glide.with(movieImage.context)
                .load(movieImageUri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(movieImage)
            titleText.text = movie?.title
            rateText.text = movie?.voteAverage.toString()
            languageValueText.text = movie?.originalLanguage
            releaseDateValueText.text = movie?.releaseDate
            durationValueText.text = "${movie?.runtime} ${getString(R.string.min)}"
            descriptionValueText.text = movie?.overview
        }
    }
}
