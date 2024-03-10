package com.jose.loginfirebasemvvm.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean> = _navigateToRegister

    fun onRegisterClick(){
        _navigateToRegister.value = false
    }

    fun onEmailChange(newEmail: String){
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
    }

    fun validateFields(){
        if(email.value.isNullOrBlank()){
            _emailError.value = "El email no puede estar vacío"
        }else{
            _emailError.value = ""
        }

        if(password.value.isNullOrBlank()){
            _passwordError.value = "La contraseña no puede estar vacía"
        }else{
            _passwordError.value = ""
        }
    }
}