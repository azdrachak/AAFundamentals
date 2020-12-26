package com.github.azdrachak.aafundamentals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.Movie

class MoviesListFragment : Fragment(), MoviesListAdapter.OnMovieClickListener {

    private var progressBar: View? = null
    private var recyclerView: RecyclerView? = null

    private val movieListViewModel: MovieListViewModel by viewModels()

    companion object {
        const val TAG = "MovieListFragment"

        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews(view)

        movieListViewModel.getMovies()

        recyclerView?.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = MoviesListAdapter(this)
        }

        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) {
            (recyclerView?.adapter as MoviesListAdapter).setMovies(it)
        }

        movieListViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            progressBar?.visibility = if (it) View.VISIBLE else View.GONE
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        recyclerView = null
        progressBar = null

        super.onDestroyView()
    }

    override fun onMovieClick(movie: Movie) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragmentContainer,
                MoviesDetailsFragment.newInstance(movie.id),
                MoviesDetailsFragment.TAG
            )
            .addToBackStack(MoviesDetailsFragment.TAG)
            .commit()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.movies)
        progressBar = view.findViewById(R.id.progress)
    }
}
