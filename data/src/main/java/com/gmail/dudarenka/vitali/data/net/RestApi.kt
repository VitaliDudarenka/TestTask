package com.gmail.dudarenka.vitali.data.net

import com.gmail.dudarenka.vitali.data.entity.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface RestApi {
    @GET("users")
    fun getUsers(): Observable<List<UserResponse>>

    @GET("users/{login}")
    fun getUserByLogin(@Path("login") id: String): Observable<UserResponse>

}