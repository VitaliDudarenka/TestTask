package com.gmail.dudarenka.vitali.data.entity

import com.gmail.dudarenka.vitali.domain.entity.User

fun UserResponse.transformToDomain(): User {
    return User(id = id, name = name, avatarUrl = avatarUrl, email = email,
            login = login, organization = organization, followers = followers, createDate = createDate, following = following)
}
