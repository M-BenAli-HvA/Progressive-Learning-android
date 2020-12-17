package com.example.progressivelearning_android.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.progressivelearning_android.api.ProgressiveLearningApi
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Resource
import com.example.progressivelearning_android.model.URLType
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.services.LearningGoalApiService
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit

class LearningGoalRepository {

    private val TAG = "LearningGoalRepository"
    private var resources: ArrayList<Resource> = arrayListOf(Resource(
            "https://en.wikipedia.org/wiki/Representational_state_transfer",
            URLType.WIKIPEDIA))
    private var units: ArrayList<Unit> = arrayListOf(Unit("History", false,
    "History of REST", resources),
        Unit("Principles in-depth", false,
                "Principles of REST"))
//    private var learningGoals: ArrayList<LearningGoal> = arrayListOf(
//            LearningGoal("Understanding the REST Principles",
//                   "REST principles", 0, units)
//    )

    private val plApi: Retrofit = ProgressiveLearningApi.createApi()
    private val learningGoalApiService: LearningGoalApiService = plApi.create(LearningGoalApiService::class.java)
    private val _learningGoals: MutableLiveData<ArrayList<LearningGoal>> = MutableLiveData()


    val learningGoals: LiveData<ArrayList<LearningGoal>>
        get() = _learningGoals


    fun addLearningGoal(learningGoal: LearningGoal) {
        learningGoals.value!!.add(learningGoal)
    }

    fun deleteLearningGoal(learningGoal: LearningGoal) {
        learningGoals.value!!.remove(learningGoal)
    }

    suspend fun getLearningGoals(): ArrayList<LearningGoal>? {
        try  {
            val result=
                withTimeout(5_000) {
                    learningGoalApiService.getLearningGoals()
                }
            Log.d("lGRepository", result.toString())
            _learningGoals.value = result
        } catch(e: Error) {
            Log.d(TAG, e.message.toString())
        }
        return _learningGoals.value
    }


}