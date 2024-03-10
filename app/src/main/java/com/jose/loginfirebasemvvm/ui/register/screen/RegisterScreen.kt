package com.jose.loginfirebasemvvm.ui.register.screen

import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.jose.loginfirebasemvvm.navigation.Routes
import com.jose.loginfirebasemvvm.ui.login.content.LoginContent
import com.jose.loginfirebasemvvm.ui.login.viewmodel.LoginViewModel
import com.jose.loginfirebasemvvm.ui.register.content.RegisterContent
import com.jose.loginfirebasemvvm.ui.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavHostController){
    Scaffold { paddingValues ->
        val name by registerViewModel.name.observeAsState("")
        val nameError by registerViewModel.errorName.observeAsState("")
        val surname by registerViewModel.surname.observeAsState("")
        val surnameError by registerViewModel.errorSurname.observeAsState("")
        val email by registerViewModel.email.observeAsState("")
        val emailError by registerViewModel.emailError.observeAsState("")
        val password by registerViewModel.password.observeAsState("")
        val passwordError by registerViewModel.passwordError.observeAsState("")
        val repeatPassword by registerViewModel.repeatPassword.observeAsState("")
        val repeatPasswordError by registerViewModel.repeatPasswordError.observeAsState("")
        val toastMessage by registerViewModel.toastMessage.observeAsState("")
        val visibleToast by registerViewModel.isVisibleToast.observeAsState(false)
        if (visibleToast) {
            toastMessage.let { message ->
                Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            }
            registerViewModel.toastDisplayed()
        }
        RegisterContent(
            registerViewModel,
            paddingValues,
            name,
            onNameChange = {newName -> registerViewModel.onNameChange(newName)},
            nameError,
            surname,
            onSurnameChange = {newSurname -> registerViewModel.onSurnameChange(newSurname)},
            surnameError,
            email,
            emailError,
            onEmailChange = {newEmail -> registerViewModel.onEmailChange(newEmail)},
            password,
            onPasswordChange = {newPassword -> registerViewModel.onPasswordChange(newPassword)},
            passwordError,
            repeatPassword,
            onRepeatPasswordChange = {newRepeatPassword -> registerViewModel.onRepeatPasswordChange(newRepeatPassword)},
            repeatPasswordError
        )
    }
}