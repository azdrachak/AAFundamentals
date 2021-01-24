package com.github.azdrachak.aafundamentals.data.room

import androidx.room.*
import com.github.azdrachak.aafundamentals.data.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    suspend fun getMovieById(id: Int): Movie

    @Update
    suspend fun updateMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(movies: List<Movie>)
}
