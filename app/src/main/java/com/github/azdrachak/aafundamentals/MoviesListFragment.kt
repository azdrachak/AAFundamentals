package com.github.azdrachak.aafundamentals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.Movie

class MoviesListFragment : Fragment(),
    MoviesListAdapter.OnMovieClickListener {

    private var movies: List<Movie> = listOf()

    companion object {
        const val TAG = "MovieListFragment"
        const val MOVIE_ID = "movieId"

        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movies = MainActivity.movies

        val recyclerView = view.findViewById<RecyclerView>(R.id.movies)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = MoviesListAdapter(this)
        (recyclerView.adapter as MoviesListAdapter).setMovies(movies)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movie.id)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragmentContainer,
                MoviesDetailsFragment.newInstance(bundle),
                MoviesDetailsFragment.TAG
            )
            .addToBackStack(MoviesDetailsFragment.TAG)
            .commit()
    }
}
