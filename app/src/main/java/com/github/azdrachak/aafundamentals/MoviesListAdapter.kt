package com.github.azdrachak.aafundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.azdrachak.aafundamentals.data.Genre
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.databinding.ViewHolderMovieBinding

class MoviesListAdapter(private val movieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieHolder>() {

    private var movieList: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun setMovies(newMovies: List<Movie>) {
        movieList = newMovies
        notifyDataSetChanged()
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}

class MovieHolder(
    movieItem: View,
    private val movieClickListener: MoviesListAdapter.OnMovieClickListener
) :
    RecyclerView.ViewHolder(movieItem) {

    private val binding = ViewHolderMovieBinding.bind(movieItem)

    fun bind(movie: Movie) {
        binding.movieBackground.load(movie.poster)
        binding.pgRating.setImageResource(getPgRatingImage(movie.minimumAge))
        binding.like.setImageResource(R.drawable.like)
        binding.cardName.text = movie.title
        binding.minutes.text = "${movie.runtime} MIN"
        binding.ratingBar.rating = convertRating(movie.ratings)
        binding.reviews.text = "${movie.numberOfRatings} REVIEWS"
        binding.tagLine.text = getTags(movie.genres)

        binding.movieClick.setOnClickListener { movieClickListener.onMovieClick(movie) }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    private fun getPgRatingImage(minimumAge: Int): Int =
        if (minimumAge > 13) R.drawable.pg16 else R.drawable.pg13

    private fun getTags(genres: List<Genre>): String = genres.joinToString(", ") { it.name }
}
