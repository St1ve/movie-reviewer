package com.razdyakonov.movie_reviewer.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class AbsPaginationLinearOnScrollListener(
    private val linearLayoutManager: LinearLayoutManager,
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

        if (isLastPage() || isLoading() || isError()) {
            return
        }

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
            firstVisibleItemPosition >= 0
        ) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    open fun isError(): Boolean {
        return false
    }
}
