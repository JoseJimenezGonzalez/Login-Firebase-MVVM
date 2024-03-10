package com.jose.loginfirebasemvvm.ui.login.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.jose.loginfirebasemvvm.navigation.Routes
import com.jose.loginfirebasemvvm.ui.login.content.LoginContent
import com.jose.loginfirebasemvvm.ui.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController){
    Scaffold { paddingValues ->
        val email by loginViewModel.email.observeAsState("")
        val password by loginViewModel.password.observeAsState("")
        val emailError by loginViewModel.emailError.observeAsState("")
        val passwordError by loginViewModel.passwordError.observeAsState("")
        LoginContent(
            paddingValues,
            email,
            password,
            emailError,
            passwordError,
            onEmailChange = { newEmail -> loginViewModel.onEmailChange(newEmail) },
            onPasswordChange = { newPassword -> loginViewModel.onPasswordChange(newPassword) },
            validateFields = { loginViewModel.validateFields() },
            onRegisterClick = { navController.navigate(Routes.Register.route) }
        )
    }
}