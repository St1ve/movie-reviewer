package com.razdyakonov.movie_reviewer.movie.searchmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.razdyakonov.movie_reviewer.App
import com.razdyakonov.movie_reviewer.movie.data.MovieInteractor
import com.razdyakonov.movie_reviewer.movie.model.Movie
import com.razdyakonov.movie_reviewer.utils.SingleLiveEvent

class MovieViewModel : ViewModel() {

    private val moviesInteractor = App.instance?.movieInteractor

    private val _errorLiveData = SingleLiveEvent<String>()
    val errorLiveData: LiveData<String> = _errorLiveData
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    init {
        loadMovies()
    }

    fun loadMovies() {
        if (moviesInteractor == null) {
            Log.d(TAG, "loadMovies: Error")
            _errorLiveData.value = "Can't get movie interactor"
            return
        }

        moviesInteractor.getMovies(object : MovieInteractor.GetMovieCallBack {
            override fun onSuccess(movies: List<Movie>) {
                _moviesLiveData.postValue(movies)
            }

            override fun onError(error: String) {
                _errorLiveData.postValue(error)
            }
        })
    }

    companion object {

        private const val TAG = "MovieViewModel"
    }
}
