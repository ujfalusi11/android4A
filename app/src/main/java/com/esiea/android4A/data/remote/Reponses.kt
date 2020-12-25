package com.esiea.android4A.data.remote
import com.esiea.android4A.domain.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    val user: User
)

data class UserTokenResponse(
    @SerializedName("user")
    val user: User,
    val token: String
)

data class ErrorMessage(val message: String)