package com.example.progressivelearning_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Resource
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.repository.LearningGoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LearningGoalViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "LearningGoalViewModel"
    private val learningGoalRepository: LearningGoalRepository = LearningGoalRepository()

    val selectedLearningGoal: MutableLiveData<LearningGoal> = MutableLiveData()
    val newUnit: MutableLiveData<Unit> = MutableLiveData()
    val learningGoals: LiveData<ArrayList<LearningGoal>> = learningGoalRepository.learningGoals


    fun getLearningGoals() {
        viewModelScope.launch {
            try {
                learningGoalRepository.getLearningGoals()
            } catch (e: Error) {
                Log.d(TAG, e.message.toString())
            }

        }
    }

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

    fun addNewUnit() {
        viewModelScope.launch {
            try {
                selectedLearningGoal.value!!.units.add(newUnit.value!!)
            } catch(e: Error) {
                Log.e(TAG, e.message!!)
            }
        }
    }

    fun setNewUnit(unit: Unit) {
        viewModelScope.launch {
            try {
                newUnit.value = unit
            } catch (e: Error) {
                Log.e(TAG, e.message!!)
            }
        }
    }

}