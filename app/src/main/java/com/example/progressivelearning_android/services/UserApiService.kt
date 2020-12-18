package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApiService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int,
                        @Header("Authorization") token: String): Response<User>

}