package com.it342.auth.api

import com.it342.auth.api.models.AuthResponse
import com.it342.auth.api.models.LoginRequest
import com.it342.auth.api.models.RegisterRequest
import com.it342.auth.api.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/register")
    suspend fun register(@Body body: RegisterRequest): Response<AuthResponse>

    @POST("api/auth/login")
    suspend fun login(@Body body: LoginRequest): Response<AuthResponse>

    @POST("api/auth/logout")
    suspend fun logout(): Response<MessageResponse>

    @GET("api/user/me")
    suspend fun getMe(): Response<UserResponse>
}

data class MessageResponse(val message: String?)
