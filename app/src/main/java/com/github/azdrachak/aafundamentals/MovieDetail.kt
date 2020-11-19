package com.github.azdrachak.aafundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.azdrachak.aafundamentals.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}