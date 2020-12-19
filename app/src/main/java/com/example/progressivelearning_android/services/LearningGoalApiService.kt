package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.LearningGoal
import retrofit2.http.*

interface LearningGoalApiService {

    @GET("learning-goals")
    suspend fun getLearningGoals(): ArrayList<LearningGoal>

    @GET("users/{id}/learning-goals")
    suspend fun getUserLearningGoals(@Path("id") userId: Int,
                                     @Header("Authorization") token: String)
            : ArrayList<LearningGoal>

    @POST("learning-goals")
    suspend fun createLearningGoal(@Body learningGoal: LearningGoal): LearningGoal
}