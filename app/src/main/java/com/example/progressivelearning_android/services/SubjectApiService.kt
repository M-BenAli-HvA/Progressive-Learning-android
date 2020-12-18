package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.Subject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SubjectApiService {

    @GET("users/{userId}/subjects")
    suspend fun getUserSubjects(@Path("userId") userId: Int,
                                @Header("Authorization") authToken: String)
            : ArrayList<Subject>

    @GET("subjects/{userId}")
    suspend fun getSubject(@Path("userId") userId: Int,
                                @Header("Authorization") authToken: String)
            : Subject

}