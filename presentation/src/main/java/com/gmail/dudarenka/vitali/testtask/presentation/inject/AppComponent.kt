package com.gmail.dudarenka.vitali.testtask.presentation.inject

import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.details.UserDetailsViewModel
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.list.UserListViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(userListViewModel: UserListViewModel)
    fun inject(userDetailsViewModel: UserDetailsViewModel)
}