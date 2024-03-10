package com.jose.loginfirebasemvvm.domain.model

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)
