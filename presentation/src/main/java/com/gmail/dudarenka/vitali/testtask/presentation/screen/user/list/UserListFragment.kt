package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.dudarenka.vitali.testtask.R
import com.gmail.dudarenka.vitali.testtask.databinding.FragmentUserListBinding
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.UserRouter


class UserListFragment : BaseMvvmFragment<UserListViewModel, UserRouter, FragmentUserListBinding>() {
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
        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.setHasFixedSize(true)
    }



}