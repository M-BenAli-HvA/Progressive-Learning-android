package com.example.progressivelearning_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.repository.LearningGoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LearningGoalViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "LearningGoalViewModel"
    private val learningGoalRepository: LearningGoalRepository = LearningGoalRepository()

    val selectedLearningGoal: MutableLiveData<LearningGoal> = MutableLiveData()
    val learningGoals = learningGoalRepository.getLearningGoals()


    fun setLearningGoal(learningGoal: LearningGoal) {
        viewModelScope.launch {
            try {
                selectedLearningGoal.value = learningGoal
            } catch(e: Error) {
                Log.e(TAG, e.message!!)
            }
        }
    }

    fun addLearningGoal(learningGoal: LearningGoal) {
        learningGoalRepository.addLearningGoal(learningGoal)
    }

    fun deleteLearningGoal(learningGoal: LearningGoal) {
        learningGoalRepository.deleteLearningGoal(learningGoal)
    }

}