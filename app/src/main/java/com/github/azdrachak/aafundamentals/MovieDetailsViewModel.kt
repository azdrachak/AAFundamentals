package com.github.azdrachak.aafundamentals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.azdrachak.aafundamentals.data.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _movieLiveData: MutableLiveData<Movie> = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie> get() = _movieLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _loadingLiveData.value = true
            _movieLiveData.value = repository.getMovieById(movieId)
            _loadingLiveData.value = false
        }
    }
}

class MovieDetailsViewModelFactory(private val repository: MoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
