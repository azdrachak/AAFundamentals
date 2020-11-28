package com.github.azdrachak.aafundamentals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    private var onClickListener: IMovieClick? = null

    companion object {
        const val TAG = "MovieListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        view.findViewById<View>(R.id.movieClick).setOnClickListener {
            onClickListener?.click()
        }

        return view
    }

    fun setListener(listener: IMovieClick) {
        onClickListener = listener
    }

    interface IMovieClick {
        fun click()
    }

}
