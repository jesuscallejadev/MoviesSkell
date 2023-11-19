package com.jesus.moviesskell.features.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.jesus.moviesskell.R
import com.jesus.moviesskell.data.response.MovieData
import com.jesus.moviesskell.databinding.MovieItemViewBinding
import com.jesus.moviesskell.network.Api

class MoviesPagerAdapter(private val movieItemClickListener: MovieItemClickListener) :
    PagingDataAdapter<MovieData, MoviesPagerAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, this.movieItemClickListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: MovieItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieData?, movieItemClickListener: MovieItemClickListener) {
            if (item != null) {
                val moviePosterURL = Api.IMAGES_BASE_URL + item.posterPath
                binding.apply {
                    binding.itemContainer.setOnClickListener {
                        movieItemClickListener.onClick(movieId = item.id)
                    }
                    titleText.text = item.title
                    rateText.text = item.voteAverage.toString()
                    languageText.text = item.originalLanguage
                    dateText.text = item.releaseDate

                    val moviePosterUri = moviePosterURL.toUri().buildUpon().scheme("https").build()
                    Glide.with(movieImage.context)
                        .load(moviePosterUri)
                        .transition(withCrossFade())
                        .placeholder(R.drawable.movies_placeholder)
                        .into(movieImage)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}

class MovieItemClickListener(val clickListener: (movieId: Int) -> Unit) {
    fun onClick(movieId: Int) = clickListener(movieId)
}