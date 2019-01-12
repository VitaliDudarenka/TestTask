package com.gmail.dudarenka.vitali.data.repositories

import com.gmail.dudarenka.vitali.data.net.RestService
import com.gmail.dudarenka.vitali.data.entity.transformToDomain
import com.gmail.dudarenka.vitali.domain.entity.User
import com.gmail.dudarenka.vitali.domain.repositories.UserRepository
import io.reactivex.Observable

class UserRepositoryImpl(private val apiService: RestService) : UserRepository {

    override fun get(count: Int): Observable<List<User>> {
        return apiService.getUsers(count).map {
            it.map {
                it.transformToDomain()
            }
        }
    }

    override fun getByLogin(login: String): Observable<User> {
        return apiService.getUserByLogin(login).map {
            it.transformToDomain()
        }
    }

}