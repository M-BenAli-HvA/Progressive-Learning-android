package com.example.progressivelearning_android.repository

import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Unit

class LearningGoalRepository {

    private var units: List<Unit> = listOf(Unit("History", false),
        Unit("Principles in-depth", false))
    private var learningGoals: ArrayList<LearningGoal> = arrayListOf(
            LearningGoal("Understanding the REST Principles",
                   "REST principles", 20, units)
    )

    fun addLearningGoal(learningGoal: LearningGoal) {
        learningGoals.add(learningGoal)
    }

    fun deleteLearningGoal(learningGoal: LearningGoal) {
        learningGoals.remove(learningGoal)
    }

    fun getLearningGoals(): ArrayList<LearningGoal> {
        return learningGoals
    }


}