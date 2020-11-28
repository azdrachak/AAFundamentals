package com.github.azdrachak.aafundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    MoviesListFragment.MovieClickListener,
    MoviesDetailsFragment.MovieDetailsClickListener {

    private var rootFragment = MoviesListFragment.newInstance()
    private var detailsFragment = MoviesDetailsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    MoviesListFragment.newInstance(),
                    MoviesListFragment.TAG
                )
                .commit()
        } else {
            val movieList = supportFragmentManager.findFragmentByTag(MoviesListFragment.TAG)
            rootFragment = movieList as MoviesListFragment

            val movieDetails = supportFragmentManager.findFragmentByTag(MoviesDetailsFragment.TAG)
            if (movieDetails != null) {
                detailsFragment = movieDetails as MoviesDetailsFragment
            }
        }
    }

    override fun onMovieClicked() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, detailsFragment, MoviesDetailsFragment.TAG)
            .addToBackStack(MoviesDetailsFragment.TAG)
            .commit()
    }

    override fun onBackButtonClicked() {
        onBackPressed()
    }
}