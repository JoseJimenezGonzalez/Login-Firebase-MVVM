package com.jose.loginfirebasemvvm.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
}