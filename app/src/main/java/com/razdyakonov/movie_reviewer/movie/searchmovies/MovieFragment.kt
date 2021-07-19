package com.razdyakonov.movie_reviewer.movie.searchmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.razdyakonov.movie_reviewer.R
import com.razdyakonov.movie_reviewer.movie.model.Movie
import com.razdyakonov.movie_reviewer.movie.recyclerview.MovieAdapter

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.movie_rv)

        val fakeMovieList = listOf(
            Movie(id = 0, name = "Avengers"),
            Movie(id = 1, name = "Superman"),
            Movie(id = 2, name = "Ironman"),
            Movie(id = 3, name = "Matrix"),
        )

        recyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val adapter = MovieAdapter(layoutInflater)
        adapter.submitList(fakeMovieList)
        recyclerView.adapter = adapter
    }
}
