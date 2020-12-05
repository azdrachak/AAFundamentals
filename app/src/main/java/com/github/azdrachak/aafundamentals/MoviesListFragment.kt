package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MoviesListFragment : Fragment() {

    private var onClickListener: MovieClickListener? = null

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
        view.findViewById<View>(R.id.movieClick).setOnClickListener {
            onClickListener?.onMovieClicked()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieClickListener) {
            onClickListener = context
        }
    }

    override fun onDetach() {
        onClickListener = null
        super.onDetach()
    }

    interface MovieClickListener {
        fun onMovieClicked()
    }

}
