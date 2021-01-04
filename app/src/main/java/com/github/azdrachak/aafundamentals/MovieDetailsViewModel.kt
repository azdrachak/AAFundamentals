package com.github.azdrachak.aafundamentals

import android.app.Application
import androidx.lifecycle.*
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.data.loadMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(private val app: Application) : AndroidViewModel(app) {

    private var _movieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = _movieLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) { loadMovies(app.applicationContext) }
            movies.singleOrNull { it.id == movieId }?.let {
                _movieLiveData.value = it
            }
        }
    }
}
