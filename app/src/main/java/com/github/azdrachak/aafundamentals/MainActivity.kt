package com.github.azdrachak.aafundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    MoviesDetailsFragment.MovieDetailsClickListener {

    private lateinit var rootFragment: MoviesListFragment
    private lateinit var detailsFragment: MoviesDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            rootFragment = MoviesListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    rootFragment,
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

    override fun onBackButtonClicked() {
        onBackPressed()
    }
}