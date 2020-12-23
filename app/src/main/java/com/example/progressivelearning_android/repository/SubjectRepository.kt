package com.example.progressivelearning_android.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.progressivelearning_android.api.ProgressiveLearningApi
import com.example.progressivelearning_android.model.Subject
import com.example.progressivelearning_android.services.SubjectApiService
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit

class SubjectRepository {

    private val TAG = "SubjectRepository"

    private val plApi: Retrofit = ProgressiveLearningApi.createApi()
    private val subjectApiService: SubjectApiService = plApi.create(SubjectApiService::class.java)
    private val _subjects: MutableLiveData<ArrayList<Subject>> = MutableLiveData()

    val subjects: LiveData<ArrayList<Subject>>
        get() = _subjects

    suspend fun getUserSubjects(userId: Int, authToken: String): ArrayList<Subject>? {
        try  {
            val completeToken = "Bearer $authToken"
            val result=
                    withTimeout(5_000) {
                        subjectApiService.getUserSubjects(userId, completeToken)
                    }
            _subjects.value = result
        } catch(e: Error) {
            Log.e(TAG, e.message.toString())
        }
        return _subjects.value
    }

}