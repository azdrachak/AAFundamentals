package com.github.azdrachak.aafundamentals.data

data class MovieData(
    val movieBackground: Int,
    val pgRating: Int,
    val like: Int,
    val movieTitle: String,
    val movieLength: Int,
    val movieRating: Int,
    val reviewsCount: Int,
    val tags: List<String>
)