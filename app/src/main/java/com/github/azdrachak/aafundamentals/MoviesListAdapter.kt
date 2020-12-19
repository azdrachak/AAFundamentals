package com.github.azdrachak.aafundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.azdrachak.aafundamentals.data.Genre
import com.github.azdrachak.aafundamentals.data.Movie

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
    private val movieItem: View,
    private val movieClickListener: MoviesListAdapter.OnMovieClickListener
) :
    RecyclerView.ViewHolder(movieItem) {
    private val movieBackground: ImageView = movieItem.findViewById(R.id.movieBackground)
    private val pgRating: ImageView = movieItem.findViewById(R.id.pgRating)
    private val like: ImageView = movieItem.findViewById(R.id.like)
    private val movieTitle: TextView = movieItem.findViewById(R.id.card_name)
    private val movieLength: TextView = movieItem.findViewById(R.id.minutes)
    private val movieRating: RatingBar = movieItem.findViewById(R.id.ratingBar)
    private val reviewsCount: TextView = movieItem.findViewById(R.id.reviews)
    private val tags: TextView = movieItem.findViewById(R.id.tagLine)
    private val clickItem: View = movieItem.findViewById(R.id.movieClick)

    fun bind(movie: Movie) {
        Glide
            .with(movieItem)
            .load(movie.poster)
            .into(movieBackground)
        pgRating.setImageResource(getPgRatingImage(movie.minimumAge))
        like.setImageResource(R.drawable.like)
        movieTitle.text = movie.title
        movieLength.text = "${movie.runtime} MIN"
        movieRating.rating = convertRating(movie.ratings)
        reviewsCount.text = "${movie.numberOfRatings} REVIEWS"
        tags.text = getTags(movie.genres)

        clickItem.setOnClickListener { movieClickListener.onMovieClick(movie) }
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    private fun getPgRatingImage(minimumAge: Int): Int =
        if (minimumAge > 13) R.drawable.pg16 else R.drawable.pg13

    private fun getTags(genres: List<Genre>): String = genres.joinToString(", ") { it.name }
}
