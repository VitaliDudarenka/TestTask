package com.gmail.dudarenka.vitali.testtask.presentation.base

import android.support.v4.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : FragmentActivity() {
    protected open val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


}