package com.esiea.android4A.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esiea.android4A.domain.entity.User

@Entity
data class UserLocal(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "nationality") val nationality: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun User.toData(): UserLocal {
    return UserLocal(name=name , email = email, password = password, gender= gender, nationality = nationality)
}

fun UserLocal.toEntity(): User {
    return User(name=name , email = email,password = password, gender= gender, nationality = nationality)
}
