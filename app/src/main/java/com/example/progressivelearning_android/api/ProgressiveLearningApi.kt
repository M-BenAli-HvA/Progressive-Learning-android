package com.example.progressivelearning_android.api

import com.example.progressivelearning_android.services.LearningGoalApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProgressiveLearningApi {

    companion object {
        const val baseURL = "https://api-progressive-learning.herokuapp.com/api/"

        fun createApi(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val progressiveLearningApi = Retrofit.Builder()
                .baseUrl(baseURL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return progressiveLearningApi
        }

    }
}