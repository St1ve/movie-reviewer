package com.razdyakonov.movie_reviewer.movie.data

import com.razdyakonov.movie_reviewer.movie.model.Movie

class FakeMovieService : IMovieService {

    override fun getMovies(requestNumber: Int): List<Movie> = List(10) { index: Int ->
        val newId = requestNumber * 10 + index
        Movie(id = newId, name = "Movie $newId")
    }
}
