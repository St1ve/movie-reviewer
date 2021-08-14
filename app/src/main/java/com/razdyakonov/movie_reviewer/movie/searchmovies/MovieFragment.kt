package com.razdyakonov.movie_reviewer.movie.searchmovies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.razdyakonov.movie_reviewer.R
import com.razdyakonov.movie_reviewer.movie.model.Movie
import com.razdyakonov.movie_reviewer.movie.recyclerview.MovieAdapter
import com.razdyakonov.movie_reviewer.utils.AbsPaginationLinearOnScrollListener

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var totalItems: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val adapter = MovieAdapter(layoutInflater)
        val rvLinearLayoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        val rvScrollListener = object : AbsPaginationLinearOnScrollListener(rvLinearLayoutManager) {

            override fun loadMoreItems() {
                viewModel.loadMovies()
            }

            override fun isLastPage(): Boolean {
                // TODO: 14.08.2021 Implement this logic in future PR
                return false
            }

            override fun isLoading(): Boolean {
                // TODO: 14.08.2021 Implement this logic in future PR
                return false
            }

            override fun isError(): Boolean {
                // TODO: 14.08.2021 Implement this logic in future PR 
                return super.isError()
            }
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_rv).apply {
            layoutManager = rvLinearLayoutManager
            this.adapter = adapter
            addOnScrollListener(rvScrollListener)
        }

        viewModel.moviesLiveData.observe(viewLifecycleOwner) { movies: List<Movie>? ->
            movies ?: return@observe
            totalItems = movies.size
            Log.d(TAG, "onViewCreated: observe $movies")
            val testAdapter = recyclerView.adapter
            if (testAdapter == null) {
                recyclerView.adapter = adapter
            } else {
                (testAdapter as MovieAdapter).submitList(movies)
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { error: String? ->
            error ?: return@observe
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        private const val TAG = "MovieFragment"
    }
}
