package com.example.progressivelearning_android.services

import com.example.progressivelearning_android.model.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface UnitApiService {

    @GET("units/{id}/resources")
    suspend fun getUnitResources(@Path("id") unitId: Int): ArrayList<Resource>

}