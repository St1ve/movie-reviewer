package com.razdyakonov.movie_reviewer

import android.app.Application
import com.razdyakonov.movie_reviewer.movie.data.FakeMovieService
import com.razdyakonov.movie_reviewer.movie.data.IMovieService
import com.razdyakonov.movie_reviewer.movie.data.MovieInteractor

class App : Application() {

    lateinit var movieService: IMovieService
    lateinit var movieInteractor: MovieInteractor

    override fun onCreate() {
        super.onCreate()

        instance = this

        initMovieService()
        initMovieInteractor()
    }

    private fun initMovieService() {
        movieService = FakeMovieService()
    }

    private fun initMovieInteractor() {
        movieInteractor = MovieInteractor(movieService)
    }

    companion object {

        var instance: App? = null
            private set
    }
}
