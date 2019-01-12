package com.gmail.dudarenka.vitali.domain.usecases

import com.gmail.dudarenka.vitali.domain.entity.User
import com.gmail.dudarenka.vitali.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.domain.repositories.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetByLoginUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                            private val userRepository: UserRepository) : BaseUseCase(postExecutorThread) {
    fun getByLogin(login: String): Observable<User> {
        return userRepository.getByLogin(login).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }
}