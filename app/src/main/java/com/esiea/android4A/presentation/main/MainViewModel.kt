package com.esiea.android4A.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.android4A.domain.entity.User
import com.esiea.android4A.domain.usecase.CreateUserUseCase
import com.esiea.android4A.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val counter : MutableLiveData<Int> = MutableLiveData()

    init{
        counter.value = 0
    }

    fun onClickedIncrement() {
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(User("test"))
            val user = getUserUseCase.invoke("test")
        }
    }
}