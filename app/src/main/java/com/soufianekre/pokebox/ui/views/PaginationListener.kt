package com.soufianekre.pokebox.ui.views

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationListener(var layoutManager :GridLayoutManager) : RecyclerView.OnScrollListener() {



    /**
     * Set scrolling threshold here (for now i'm assuming 10 item in one page)
     */

    private val PAGE_SIZE = 20

    /**
     * Supporting only LinearLayoutManager for now.
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // Linear Layout Manager

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
        /*

        // grid Layout Manager
        if (dy > 0) { // only when scrolling up
            val visibleThreshold = 2
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val currentTotalCount = layoutManager.itemCount
            if (!isLoading() && !isLastPage()){
                if (currentTotalCount <= lastItem + visibleThreshold) {
                    // show your loading view
                    // load content in background
                    loadMoreItems()
                }
            }
           }

         */

    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}