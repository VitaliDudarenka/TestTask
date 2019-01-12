package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager


abstract class EndlessRecyclerOnScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var mPreviousTotal = 0
    private var mLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }
        val visibleThreshold = 5
        if (!mLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            onLoadMore()

            mLoading = true
        }
    }

    abstract fun onLoadMore()
}
