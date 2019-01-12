package com.gmail.dudarenka.vitali.domain.repositories

import com.gmail.dudarenka.vitali.domain.entity.User
import io.reactivex.Observable

interface UserRepository : BaseRepository {
    fun get(count: Int): Observable<List<User>>
    fun getByLogin(login: String): Observable<User>
}