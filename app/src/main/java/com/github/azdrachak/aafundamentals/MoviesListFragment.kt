package com.github.azdrachak.aafundamentals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.data.getMoviesList
import com.github.azdrachak.aafundamentals.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MoviesListFragment : Fragment(), MoviesListAdapter.OnMovieClickListener {

    private val binding: FragmentMoviesListBinding get() = _binding!!

    private var _binding: FragmentMoviesListBinding? = null
    private val movieListViewModel: MovieListViewModel by viewModels()

    companion object {
        const val TAG = "MovieListFragment"

        fun newInstance(): MoviesListFragment = MoviesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CoroutineScope(Dispatchers.IO).launch {
            val movies = getMoviesList()
            Log.d(LOG_TAG, "$movies")
            Log.d(LOG_TAG, "onViewCreated: movies count ${movies.size}")
        }

        movieListViewModel.getMovies()
        binding.movies.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = MoviesListAdapter(this)
        }

        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) {
            (binding.movies.adapter as MoviesListAdapter).setMovies(it)
        }

        movieListViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null

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
}
