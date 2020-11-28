package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MoviesDetailsFragment : Fragment() {

    private var onBackButtonClickListener: MovieDetailsClickListener? = null

    companion object {
        const val TAG = "MovieDetailsFragment"

        fun newInstance(): MoviesDetailsFragment = MoviesDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.path).setOnClickListener {
            onBackButtonClickListener?.onBackButtonClicked()
        }

        super.onViewCreated(view, savedInstanceState)
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
