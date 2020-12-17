package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("authentication/sign-up")
    suspend fun createUser(@Body user: User): Response<User>

    @POST("authentication/login")
    suspend fun  loginUser(@Body user: User): Response<User>

}