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
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun setMovies(newMovies: List<MovieData>) {
        movieList = newMovies
        notifyDataSetChanged()
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: MovieData)
    }
}

class MovieHolder(movieItem: View, private val movieClickListener: MoviesListAdapter.OnMovieClickListener) :
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

    fun bind(movieData: MovieData) {
        movieBackground.setImageResource(movieData.poster)
        pgRating.setImageResource(movieData.pgRating)
        like.setImageResource(movieData.like)
        movieTitle.text = movieData.title
        movieLength.text = "${movieData.length} MIN"
        movieRating.rating =
            if (movieData.rating > 5) 5.0f else movieData.rating.toFloat()
        reviewsCount.text = "${movieData.reviewsCount} REVIEWS"
        tags.text = movieData.tags.joinToString(", ")

        clickItem.setOnClickListener { movieClickListener.onMovieClick(movieData) }
    }
}
