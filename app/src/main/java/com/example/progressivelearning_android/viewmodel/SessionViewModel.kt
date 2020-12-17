package com.example.progressivelearning_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.progressivelearning_android.model.User
import com.example.progressivelearning_android.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SessionViewModel"

    private val userRepository: UserRepository = UserRepository()
    val loggedInUser: LiveData<User?> = userRepository.loggedInUser
    val authenticationToken: LiveData<String?> = userRepository.authenticationToken

    fun signUp(email: String, password: String,
               confirmPassWord: String) {
        viewModelScope.launch {
            try {
                userRepository.signUp(email, password, confirmPassWord)
                Log.d("SessionViewModel", "Signed up")
            } catch (e: Error) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepository.login(email, password)
            } catch(e: Error) {
                Log.d(TAG, e.message.toString())
            }
        }
    }


}