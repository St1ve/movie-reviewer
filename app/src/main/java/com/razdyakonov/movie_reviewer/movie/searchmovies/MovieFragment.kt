package com.razdyakonov.movie_reviewer.movie.searchmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.razdyakonov.movie_reviewer.R
import com.razdyakonov.movie_reviewer.movie.model.Movie
import com.razdyakonov.movie_reviewer.movie.recyclerview.MovieAdapter

class MovieFragment : Fragment(R.layout.fragment_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.movie_rv)

        val fakeMovieList = mutableListOf<Movie>()

        for (i in 1..10) {
            fakeMovieList.addAll(
                listOf(
                    Movie(id = 0, name = "Avengers"),
                    Movie(id = 1, name = "Superman"),
                    Movie(id = 2, name = "Ironman"),
                    Movie(id = 3, name = "Matrix"),
                )
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val adapter = MovieAdapter(layoutInflater).apply {
            submitList(fakeMovieList)
        }
        recyclerView.adapter = adapter
    }
}
