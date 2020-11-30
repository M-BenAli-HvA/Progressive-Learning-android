package com.example.progressivelearning_android.model

data class LearningGoal(val title: String,
                        val description: String,
                        val progress: Double,
                        val units: List<Unit>,
                        val subject: Subject
)