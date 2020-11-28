package com.github.azdrachak.aafundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.IMovieClick {

    private var rootFragment = FragmentMoviesList().apply { setListener(this@MainActivity) }
    private var detailsFragment = FragmentMoviesDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, rootFragment, FragmentMoviesList.TAG)
                .commit()
        } else {
            val movieList = supportFragmentManager.findFragmentByTag(FragmentMoviesList.TAG)
            rootFragment =
                (movieList as FragmentMoviesList).apply { setListener(this@MainActivity) }

            val movieDetails = supportFragmentManager.findFragmentByTag(FragmentMoviesDetails.TAG)
            if (movieDetails != null) {
                detailsFragment = movieDetails as FragmentMoviesDetails
            }
        }
    }

    override fun click() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, detailsFragment, FragmentMoviesDetails.TAG)
            .addToBackStack(FragmentMoviesDetails.TAG)
            .commit()
    }
}