package com.example.progressivelearning_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.progressivelearning_android.model.Subject
import com.example.progressivelearning_android.repository.SubjectRepository
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "SubjectViewModel"
    private val subjectRepository: SubjectRepository = SubjectRepository()
    val subjects: LiveData<ArrayList<Subject>> = subjectRepository.subjects

    fun getUserSubjects(userId: Int, authToken: String) {
        viewModelScope.launch {
            try {
                subjectRepository.getUserSubjects(userId, authToken)
            } catch(e: Error) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

}