package com.gmail.dudarenka.vitali.testtask.presentation.screen.user.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.testtask.R
import com.gmail.dudarenka.vitali.testtask.databinding.FragmentUserDetailsBinding
import com.gmail.dudarenka.vitali.testtask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.testtask.presentation.screen.user.UserRouter


class UserDetailsFragment : BaseMvvmFragment<UserDetailsViewModel, UserRouter, FragmentUserDetailsBinding>() {


    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(login: String): UserDetailsFragment {
            val fragment = UserDetailsFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, login)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun provideViewModel(): UserDetailsViewModel {
        return ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_user_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val login = arguments?.getString(ID_EXTRA)
        if (login != null) {
            viewModel.setUserLogin(login)
        } else {
            router?.goBack()
        }
    }

}