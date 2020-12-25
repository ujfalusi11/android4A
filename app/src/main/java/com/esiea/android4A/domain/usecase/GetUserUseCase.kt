package com.esiea.android4A.domain.usecase

import com.esiea.android4A.data.repository.UserRepository
import com.esiea.android4A.domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    fun invoke(email: String,password: String) : User? {
        return userRepository.getUser(email,password)
    }
}