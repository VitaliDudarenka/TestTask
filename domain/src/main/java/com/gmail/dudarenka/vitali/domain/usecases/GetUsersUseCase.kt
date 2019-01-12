package com.gmail.dudarenka.vitali.domain.usecases

import com.gmail.dudarenka.vitali.domain.entity.User
import com.gmail.dudarenka.vitali.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.domain.repositories.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                          private val userRepository: UserRepository) : BaseUseCase(postExecutorThread) {

    fun get(count: Int): Observable<List<User>> {
        return userRepository.get(count).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

}



