package com.github.azdrachak.aafundamentals

import androidx.lifecycle.*
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.data.getMoviesList
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MovieListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _movieListLiveData: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _movieListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        viewModelScope.launch {
            _movieListLiveData.value = repository.getAllMovies()
        }
    }

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            val loadedMovies = getMoviesList()
            _movieListLiveData.value = loadedMovies
            _loadingLiveData.value = false
            repository.addMovies(loadedMovies)
        }
    }
}

class MovieListViewModelFactory(private val repository: MoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
