package com.esiea.android4A.presentation.register

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.android4A.R
//import com.esiea.android4A.data.remote.ErrorMessage
//import com.esiea.android4A.data.remote.UserTokenResponse
import com.esiea.android4A.domain.entity.User
import com.esiea.android4A.domain.usecase.CreateUserUseCase
import com.esiea.android4A.domain.usecase.GetUserUseCase
import com.esiea.android4A.presentation.login.LoginStatus
import com.esiea.android4A.presentation.login.LoginSuccess
import com.esiea.android4A.presentation.register.RegisterError
import com.esiea.android4A.presentation.register.RegisterStatus
import com.esiea.android4A.presentation.register.RegisterSuccess
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception



class RegisterViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel(){

    val registerLiveData : MutableLiveData<RegisterStatus> = MutableLiveData()

    fun onClickedRegister(name: String, email: String, password: String, gender: String) {
        viewModelScope.launch {

            val registerStatus = if (name != "" && email != "" && password != "" && gender != "" ) {
                withContext(Dispatchers.IO) {
                    createUserUseCase.invoke(User(name, email, password, gender))
                }
                RegisterSuccess
            } else {
                RegisterError
            }

            withContext(Dispatchers.Main) {
                registerLiveData.value = registerStatus
            }

        }

    }


}