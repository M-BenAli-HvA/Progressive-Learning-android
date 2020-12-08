package com.example.progressivelearning_android.model

data class LearningGoal(val title: String,
                        val description: String,
                        val progress: Int = 0,
                        val units: List<Unit> = arrayListOf(),
                        val subject: Subject? = null
)