package com.example.progressivelearning_android.repository

import android.util.Log
import com.example.progressivelearning_android.api.ProgressiveLearningApi
import com.example.progressivelearning_android.model.Resource
import com.example.progressivelearning_android.services.UnitApiService
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit

class UnitRepository {

    private val TAG = "UnitRepository"

    private val plApi: Retrofit = ProgressiveLearningApi.createApi()
    private val unitApiService: UnitApiService = plApi.create(UnitApiService::class.java)

    suspend fun getUnitResources(unitId: Int): ArrayList<Resource> {
        val resources: ArrayList<Resource> = arrayListOf()
        try  {
            val result=
                    withTimeout(5_000) {
                        unitApiService.getUnitResources(unitId)
                    }
            resources.addAll(result)
        } catch(e: Error) {
            Log.e(TAG, e.message.toString())
        }
        return resources
    }



}