package com.razdyakonov.movie_reviewer.movie.data

import com.razdyakonov.movie_reviewer.movie.model.Movie

class MovieInteractor(private val movieService: IMovieService) {

    private var requestNumber: Int = 0
    private val tmpMovies = mutableListOf<Movie>()

    fun getMovies(callback: GetMovieCallBack) {
        val movies = movieService.getMovies(requestNumber)
        if (movies.isEmpty()) {
            callback.onError("Empty list")
            return
        }
        tmpMovies.addAll(movies)
        requestNumber++
        callback.onSuccess(ArrayList(tmpMovies))
    }

    interface GetMovieCallBack {
        fun onSuccess(movies: List<Movie>)
        fun onError(error: String)
    }
}
