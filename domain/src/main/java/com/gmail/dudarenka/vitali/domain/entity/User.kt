package com.gmail.dudarenka.vitali.domain.entity


data class User(val id: String, val login: String, val avatarUrl: String,
                val name: String?, val email: String?, val organization: String?, val followers: Int?,
                val following: Int?, val createDate: String?) : DomainEntity {

}