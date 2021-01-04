package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.azdrachak.aafundamentals.data.Genre
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.databinding.FragmentMoviesDetailsBinding

class MoviesDetailsFragment : Fragment() {

    private var onBackButtonClickListener: MovieDetailsClickListener? = null

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding: FragmentMoviesDetailsBinding
        get() = _binding!!

    companion object {
        const val TAG = "MovieDetailsFragment"
        private const val MOVIE_ID = "movieId"

        fun newInstance(movieId: Int): MoviesDetailsFragment {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieId)
            val fragment = MoviesDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movieId = arguments?.getInt(MOVIE_ID)

        movieDetailsViewModel.getMovie(movieId!!)

        binding.path.setOnClickListener {
            onBackButtonClickListener?.onBackButtonClicked()
        }

        binding.actors.apply {
            adapter = MovieDetailsAdapter()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        movieDetailsViewModel.movieLiveData.observe(viewLifecycleOwner) { movie: Movie ->
            binding.poster.load(movie.backdrop)
            binding.name.text = movie.title
            binding.pgRating.text = getPgRating(movie.minimumAge)
            binding.storylineText.text = movie.overview
            binding.ratingBar.rating = convertRating(movie.ratings)
            binding.tagLine.text = getTags(movie.genres)
            val reviewsCountText = "${movie.numberOfRatings} REVIEWS"
            binding.reviews.text = reviewsCountText
            if (movie.actors.isEmpty()) view.findViewById<TextView>(R.id.cast).visibility =
                View.GONE
            (binding.actors.adapter as MovieDetailsAdapter).updateActors(movie.actors)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getPgRating(minimumAge: Int): String {
        val resId: Int = if (minimumAge < 16) R.string.pg_rating13 else R.string.pg_rating16
        return getString(resId)
    }

    private fun convertRating(rating10: Float): Float = rating10 / 2.0f

    private fun getTags(genres: List<Genre>): String = genres.joinToString(", ") { it.name }

    override fun onAttach(context: Context) {
        if (context is MovieDetailsClickListener) {
            onBackButtonClickListener = context
        }
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MovieDetailsClickListener {
        fun onBackButtonClicked()
    }
}
