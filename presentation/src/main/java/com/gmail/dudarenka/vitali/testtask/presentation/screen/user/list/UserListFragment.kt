package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.gmail.dudarenka.vitali.testtask.R
import com.gmail.dudarenka.vitali.testtask.databinding.FragmentUserListBinding
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.UserRouter


class UserListFragment : BaseMvvmFragment<UserListViewModel, UserRouter, FragmentUserListBinding>() {
    private var layoutManager: LinearLayoutManager? = null

    companion object {
        fun getInstance(): UserListFragment {
            return UserListFragment()
        }
    }

    override fun provideViewModel(): UserListViewModel {
        return ViewModelProviders.of(this).get(UserListViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_user_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRecyclerView.adapter = viewModel.adapter
        layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.setHasFixedSize(true)
        binding.listRecyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager!!) {
            override fun onLoadMore() {
                viewModel.loadItems()
            }
        })

        val swipeContainer: SwipeRefreshLayout = view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            viewModel.adapter!!.clear()
            viewModel.loadItems()
            swipeContainer.isRefreshing = false
        }

    }
}