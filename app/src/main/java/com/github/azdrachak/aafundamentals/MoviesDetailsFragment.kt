package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.azdrachak.aafundamentals.data.Movie

class MoviesDetailsFragment : Fragment() {

    private lateinit var movie: Movie

    private var onBackButtonClickListener: MovieDetailsClickListener? = null

    private lateinit var recyclerView: RecyclerView

    companion object {
        const val TAG = "MovieDetailsFragment"

        fun newInstance(bundle: Bundle): MoviesDetailsFragment {
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

        val poster = view.findViewById<ImageView>(R.id.poster)
        val title = view.findViewById<TextView>(R.id.name)
        val pgRating = view.findViewById<TextView>(R.id.pgRating)
        val description = view.findViewById<TextView>(R.id.storylineText)

        val movieId = arguments?.getInt(MoviesListFragment.MOVIE_ID)
        movie = MainActivity.movies.single { it.id == movieId }

        view.findViewById<TextView>(R.id.path).setOnClickListener {
            onBackButtonClickListener?.onBackButtonClicked()
        }

        recyclerView = view.findViewById(R.id.actors)
        recyclerView.adapter = MovieDetailsAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        (recyclerView.adapter as MovieDetailsAdapter).updateActors(movie.actors)
        if (movie.actors.isEmpty()) view.findViewById<TextView>(R.id.cast).visibility = View.GONE

        Glide.with(view).load(movie.backdrop).into(poster)
        title.text = movie.title
        pgRating.text = getPgRating(movie.minimumAge)
        description.text = movie.overview

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getPgRating(minimumAge: Int): String {
        val resId: Int = if (minimumAge < 16) R.string.pg_rating13 else R.string.pg_rating16
        return getString(resId)
    }

    override fun onAttach(context: Context) {
        if (context is MovieDetailsClickListener) {
            onBackButtonClickListener = context
        }
        super.onAttach(context)
    }

    interface MovieDetailsClickListener {
        fun onBackButtonClicked()
    }
}
