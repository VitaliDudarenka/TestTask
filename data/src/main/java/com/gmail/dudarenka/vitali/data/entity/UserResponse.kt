package com.gmail.dudarenka.vitali.data.entity

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("id")
                        val id: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("login")
                        val login: String,
                        @SerializedName("avatar_url")
                        val avatarUrl: String,
                        @SerializedName("email")
                        val email: String,
                        @SerializedName("company")
                        val organization: String,
                        @SerializedName("followers")
                        val followers: Int,
                        @SerializedName("following")
                        val following: Int,
                        @SerializedName("created_at")
                        val createDate: String) : DataEntity {
}



