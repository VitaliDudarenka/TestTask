package com.gmail.dudarenka.vitali.testtask.presentation.screen.user

import com.gmail.dudarenka.vitali.testtask.R
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseRouter
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.details.UserDetailsFragment
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list.UserListFragment


class UserRouter(activity: UserActivity) : BaseRouter<UserActivity>(activity) {

    fun goToUserList() {
        replaceFragment(activity.supportFragmentManager, UserListFragment.getInstance(), R.id.container, false)
    }

    fun goToUserDetails(login: String) {
            replaceFragment(activity.supportFragmentManager, UserDetailsFragment.getInstance(login), R.id.container, true)
    }


}