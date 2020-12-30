package com.esiea.android4A.data.repository

import com.esiea.android4A.data.local.DatabaseDao
import com.esiea.android4A.data.local.models.toData
import com.esiea.android4A.data.local.models.toEntity
import com.esiea.android4A.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {

    fun createUser(user: User)  {
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String, password: String) : User? {
        val userLocal = databaseDao.findUser(email,password)
        return userLocal?.toEntity()
    }
}