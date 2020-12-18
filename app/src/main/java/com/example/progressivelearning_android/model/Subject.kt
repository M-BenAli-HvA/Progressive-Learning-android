package com.example.progressivelearning_android.model

import com.google.gson.annotations.SerializedName

data class Subject(@SerializedName("name") val title: String,
                   @SerializedName("description") val description: String? = null,
                   @SerializedName("learningGoals") val learningGoals: ArrayList<LearningGoal> = arrayListOf()) {

    override fun toString(): String {
        return this.title
    }
}
