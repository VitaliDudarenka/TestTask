package com.gmail.dudarenka.vitali.testtask.presentation.screen.user

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dudarenka.vitali.testtask.R
import com.gmail.dudarenka.vitali.testtask.databinding.ActivityUserBinding
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseMvvmActivity


private const val ID_EXTRA = "ID_EXTRA"

class UserActivity : BaseMvvmActivity<UserViewModel, UserRouter, ActivityUserBinding>() {


    override fun provideRouter(): UserRouter {
        return UserRouter(this)
    }

    override fun provideViewModel(): UserViewModel {
        return ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getStringExtra(ID_EXTRA) == null) {
            router.goToUserList()
        } else {
            router.goToUserDetails(intent.getStringExtra(ID_EXTRA))
        }
    }

}


