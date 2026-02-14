package com.it342.auth.api.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val token: String,
    val type: String? = "Bearer",
    val id: Long,
    val username: String,
    val email: String,
    @SerializedName("createdAt") val createdAt: String? = null
)
