package com.github.azdrachak.aafundamentals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.Actor

class MoviesDetailsFragment : Fragment() {

    private val actors: List<Actor> = listOf(
        Actor(R.drawable.downey, "Robert Dawney"),
        Actor(R.drawable.evans, "Chris Evans"),
        Actor(R.drawable.ruffalo, "Mark Ruffalo"),
        Actor(R.drawable.hemsworth, "Chris Hemsworth")
    )

    private var onBackButtonClickListener: MovieDetailsClickListener? = null

    private lateinit var recyclerView: RecyclerView

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

        recyclerView = view.findViewById(R.id.actors)
        recyclerView.adapter = MovieDetailsAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        (recyclerView.adapter as MovieDetailsAdapter).updateActors(actors)

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
