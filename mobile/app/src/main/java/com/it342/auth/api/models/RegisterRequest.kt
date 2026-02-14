package com.it342.auth.api.models

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
