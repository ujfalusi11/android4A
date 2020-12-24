package com.esiea.android4A.domain.usecase

import com.esiea.android4A.data.repository.UserRepository
import com.esiea.android4A.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}