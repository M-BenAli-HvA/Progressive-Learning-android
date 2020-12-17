package com.example.progressivelearning_android.model

import com.google.gson.annotations.SerializedName

data class Subject(@SerializedName("name") val title: String,
                   @SerializedName("description") val description: String,
                   @SerializedName("learningGoals") val learningGoals: List<LearningGoal>)
