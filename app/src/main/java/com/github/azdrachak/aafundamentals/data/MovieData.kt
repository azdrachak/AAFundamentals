package com.github.azdrachak.aafundamentals.data

data class MovieData(
    val poster: Int,
    val pgRating: Int,
    val like: Int,
    val title: String,
    val length: Int,
    val rating: Int,
    val reviewsCount: Int,
    val tags: List<String>
)