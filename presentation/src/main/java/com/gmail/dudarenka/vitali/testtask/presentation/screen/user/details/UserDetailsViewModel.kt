package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.details

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.domain.usecases.GetByLoginUseCase
import com.gmail.dudarenka.vitali.testtask.presentation.app.App
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.UserRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class UserDetailsViewModel : BaseViewModel<UserRouter>() {
    val name = ObservableField<String>(" ")
    val avatarUrl = ObservableField<String>(" ")
    val email = ObservableField<String>(" ")
    val organization = ObservableField<String>(" ")
    val date = ObservableField<String>(" ")
    val followers = ObservableField<Int>(0)
    val following = ObservableField<Int>(0)
    private var userLogin: String? = null
    val isProgressEnabled = ObservableBoolean(false)
    @Inject
    lateinit var userByLoginUseCase: GetByLoginUseCase

    fun setUserLogin(login: String) {
        if (userLogin != null) return
        userLogin = login
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = userByLoginUseCase.getByLogin(login).subscribeBy(
                onNext = {
                    name.set(it.name)
                    avatarUrl.set(it.avatarUrl)
                    email.set(it.email)
                    if (it.organization != null) {
                        organization.set(it.organization)
                    } else {
                        organization.set(" ")
                    }
                    followers.set(it.followers)
                    following.set(it.following)
                    date.set(it.createDate!!.substring(0, 10))
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

}
