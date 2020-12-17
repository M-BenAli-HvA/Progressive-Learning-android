package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.LearningGoal
import retrofit2.http.GET

interface LearningGoalApiService {

    @GET("learning-goals")
    suspend fun getLearningGoals(): ArrayList<LearningGoal>
}