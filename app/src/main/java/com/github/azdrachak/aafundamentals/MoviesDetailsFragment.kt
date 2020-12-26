package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.azdrachak.aafundamentals.data.Genre
import com.github.azdrachak.aafundamentals.data.Movie

class MoviesDetailsFragment : Fragment() {

    private var onBackButtonClickListener: MovieDetailsClickListener? = null

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    private var recyclerView: RecyclerView? = null
    private var poster: ImageView? = null
    private var title: TextView? = null
    private var pgRating: TextView? = null
    private var description: TextView? = null
    private var backButton: TextView? = null
    private var tagline: TextView? = null
    private var reviewsCount: TextView? = null
    private var ratingBar: RatingBar? = null

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
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movieId = arguments?.getInt(MOVIE_ID)
        initViews(view)

        movieDetailsViewModel.getMovie(movieId!!)

        backButton?.setOnClickListener {
            onBackButtonClickListener?.onBackButtonClicked()
        }

        recyclerView?.let {
            it.adapter = MovieDetailsAdapter()
            it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        movieDetailsViewModel.movieLiveData.observe(viewLifecycleOwner) { movie: Movie ->
            poster?.load(movie.backdrop)
            title?.text = movie.title
            pgRating?.text = getPgRating(movie.minimumAge)
            description?.text = movie.overview
            ratingBar?.rating = convertRating(movie.ratings)
            tagline?.text = getTags(movie.genres)
            val reviewsCountText = "${movie.numberOfRatings} REVIEWS"
            reviewsCount?.text = reviewsCountText
            if (movie.actors.isEmpty()) view.findViewById<TextView>(R.id.cast).visibility =
                View.GONE
            (recyclerView?.adapter as MovieDetailsAdapter).updateActors(movie.actors)
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
        recyclerView = null
        poster = null
        title = null
        pgRating = null
        description = null
        backButton = null
        tagline = null
        ratingBar = null
        reviewsCount = null
    }

    interface MovieDetailsClickListener {
        fun onBackButtonClicked()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.actors)
        poster = view.findViewById(R.id.poster)
        title = view.findViewById(R.id.name)
        pgRating = view.findViewById(R.id.pgRating)
        description = view.findViewById(R.id.storylineText)
        backButton = view.findViewById(R.id.path)
        tagline = view.findViewById(R.id.tagLine)
        ratingBar = view.findViewById(R.id.ratingBar)
        reviewsCount = view.findViewById(R.id.reviews)
    }
}
