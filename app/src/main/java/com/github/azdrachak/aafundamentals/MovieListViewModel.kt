package com.github.azdrachak.aafundamentals

import android.app.Application
import androidx.lifecycle.*
import com.github.azdrachak.aafundamentals.data.Movie
import com.github.azdrachak.aafundamentals.data.loadMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val app: Application) : AndroidViewModel(app) {

    private var _movieListLiveData: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    val movieListLiveData: LiveData<List<Movie>>
        get() = _movieListLiveData
    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    fun getMovies() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            _movieListLiveData.value =
                withContext(Dispatchers.IO) { loadMovies(app.applicationContext) }
            _loadingLiveData.value = false
        }
    }
}
