package com.github.azdrachak.aafundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.MovieData

class MoviesListAdapter(private val movieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieHolder>() {

    private var movieList: List<MovieData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener { movieClickListener.onMovieClick(movieList[position]) }
    }

    override fun getItemCount(): Int = movieList.size

    fun setMovies(newMovies: List<MovieData>) {
        movieList = newMovies
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: MovieData)
    }
}

class MovieHolder(movieItem: View) : RecyclerView.ViewHolder(movieItem) {
    private val movieBackground: ImageView = movieItem.findViewById(R.id.movieBackground)
    private val pgRating: ImageView = movieItem.findViewById(R.id.pgRating)
    private val like: ImageView = movieItem.findViewById(R.id.like)
    private val movieTitle: TextView = movieItem.findViewById(R.id.card_name)
    private val movieLength: TextView = movieItem.findViewById(R.id.minutes)
    private val movieRating: RatingBar = movieItem.findViewById(R.id.ratingBar)
    private val reviewsCount: TextView = movieItem.findViewById(R.id.reviews)
    private val tags: TextView = movieItem.findViewById(R.id.tagLine)

    fun bind(movieData: MovieData) {
        movieBackground.setImageResource(movieData.movieBackground)
        pgRating.setImageResource(movieData.pgRating)
        like.setImageResource(movieData.like)
        movieTitle.text = movieData.movieTitle
        movieLength.text = "${movieData.movieLength} MIN"
        movieRating.rating =
            if (movieData.movieRating > 5) 5.0f else movieData.movieRating.toFloat()
        reviewsCount.text = "${movieData.reviewsCount} REVIEWS"
        tags.text = movieData.tags.joinToString(", ")
    }
}
