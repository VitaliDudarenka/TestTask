package com.gmail.dudarenka.vitali.data.net

import com.gmail.dudarenka.vitali.data.entity.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RestApi {
    @GET("users")
    fun getUsers(@Query("since") count: String): Observable<List<UserResponse>>

    @GET("users/{login}")
    fun getUserByLogin(@Path("login") login: String): Observable<UserResponse>

}



