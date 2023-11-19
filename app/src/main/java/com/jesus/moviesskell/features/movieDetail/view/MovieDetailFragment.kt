package com.jesus.moviesskell.features.movieDetail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.jesus.moviesskell.R
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.databinding.FragmentMovieDetailBinding
import com.jesus.moviesskell.features.movieDetail.viewModel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jesus.moviesskell.domain.Result
import com.jesus.moviesskell.network.Api

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

        this.viewModel.getMovieDetail(movieId = this.movieId)
        this.onEventChange()
        return binding.root
    }

    private fun onEventChange() {
        viewModel.movieStatus.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    setMovieDetails(movie = it.data)
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

    private fun setMovieDetails(movie: MovieData?) {
        val moviePosterURL = Api.IMAGES_BASE_URL + movie?.posterPath
        binding.apply {
            movieImage.load(moviePosterURL) {
                crossfade(true)
                placeholder(R.drawable.movies_placeholder)
                scale(Scale.FILL)
            }
            titleText.text = movie?.title
            rateText.text = movie?.voteAverage.toString()
            languageValueText.text = movie?.originalLanguage
            releaseDateValueText.text = movie?.releaseDate
            //TODO: REMOVE HARDCODED
            durationValueText.text = movie?.runtime.toString() + "min"
            descriptionValueText.text = movie?.overview
        }
    }
}
