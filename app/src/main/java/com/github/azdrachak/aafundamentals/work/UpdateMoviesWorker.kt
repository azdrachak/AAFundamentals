package com.github.azdrachak.aafundamentals.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.azdrachak.aafundamentals.LOG_TAG
import com.github.azdrachak.aafundamentals.MoviesRepository
import com.github.azdrachak.aafundamentals.data.getMoviesList
import com.github.azdrachak.aafundamentals.data.room.MoviesDatabase
import kotlinx.serialization.ExperimentalSerializationApi

class UpdateMoviesWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val repository =
        MoviesRepository(MoviesDatabase.getInstance(applicationContext).movieDao())

    @ExperimentalSerializationApi
    override suspend fun doWork(): Result {
        return try {
            Log.d(LOG_TAG, "Updating movies in database")

            val movies = getMoviesList()
            Log.d(LOG_TAG, "Movies from Worker: $movies")

            repository.addMovies(movies)
            Result.success()
        } catch (e: Exception) {
            Log.d(LOG_TAG, "Can't get movies data: $e")
            Result.failure()
        }
    }
}
