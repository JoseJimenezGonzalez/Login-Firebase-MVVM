package com.jose.loginfirebasemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jose.loginfirebasemvvm.navigation.Routes
import com.jose.loginfirebasemvvm.ui.login.screen.LoginScreen
import com.jose.loginfirebasemvvm.ui.login.viewmodel.LoginViewModel
import com.jose.loginfirebasemvvm.ui.register.screen.RegisterScreen
import com.jose.loginfirebasemvvm.ui.register.viewmodel.RegisterViewModel
import com.jose.loginfirebasemvvm.ui.theme.LoginFirebaseMVVMTheme

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginFirebaseMVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }

    @Composable
    fun AppNavigator(){
        val navigationController = rememberNavController()
        NavHost(navController = navigationController, startDestination = Routes.Login.route){
            composable(Routes.Login.route){ LoginScreen(loginViewModel, navigationController)}
            composable(Routes.Register.route){ RegisterScreen(registerViewModel, navigationController)}
        }
    }
}