package com.github.azdrachak.aafundamentals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.MovieData

class MoviesListFragment(val clickListener: MoviesListAdapter.OnMovieClickListener) : Fragment() {

    private var movies: List<MovieData> = listOf(
        MovieData(
            R.drawable.avengers_small,
            R.drawable.pg13,
            R.drawable.like,
            "Avengers: End Game",
            137,
            4,
            125,
            listOf("Action", "Adventure", "Fantasy")
        ),
        MovieData(
            R.drawable.tenet,
            R.drawable.pg16,
            R.drawable.like,
            "Tenet",
            97,
            5,
            98,
            listOf("Action", "Sci-Fi", "Thriller")
        ),
        MovieData(
            R.drawable.black_widow,
            R.drawable.pg13,
            R.drawable.like,
            "Black widow",
            102,
            4,
            38,
            listOf("Action", "Adventure", "Sci-Fi")
        ),
        MovieData(
            R.drawable.superwoman,
            R.drawable.pg13,
            R.drawable.like,
            "Wonder Woman",
            120,
            5,
            74,
            listOf("Action", "Adventure", "Fantasy")
        )
    )

    companion object {
        const val TAG = "MovieListFragment"

        fun newInstance(clickListener: MoviesListAdapter.OnMovieClickListener): MoviesListFragment =
            MoviesListFragment(clickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.movies)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = MoviesListAdapter(object : MoviesListAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MovieData) {
                clickListener.onMovieClick(movie)
            }
        })
        (recyclerView.adapter as MoviesListAdapter).setMovies(movies)

        super.onViewCreated(view, savedInstanceState)
    }

}
