package com.razdyakonov.movie_reviewer.movie.data

import com.razdyakonov.movie_reviewer.movie.model.Movie

interface IMovieService {
    fun getMovies(requestNumber: Int): List<Movie>
}
