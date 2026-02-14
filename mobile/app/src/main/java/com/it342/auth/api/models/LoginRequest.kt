package com.it342.auth.api.models

data class LoginRequest(
    val email: String,
    val password: String
)
