package com.esiea.android4A.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.android4A.R
import com.esiea.android4A.domain.usecase.CreateUserUseCase
import com.esiea.android4A.domain.usecase.GetUserUseCase
import com.esiea.android4A.presentation.login.LoginStatus
import com.esiea.android4A.presentation.login.LoginSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(email,password)
            val loginStatus = if (user != null){
                LoginSuccess
            }else{
                LoginNotFound
            }

            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }
}
