package com.github.azdrachak.aafundamentals

import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.data.room.MovieDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val moviesDao: MovieDAO) {
    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDao.getAll()
    }

    suspend fun getMovieById(id: Int): Movie = withContext(Dispatchers.IO) {
        moviesDao.getMovieById(id)
    }

    suspend fun addMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        moviesDao.addAll(movies)
    }
}
