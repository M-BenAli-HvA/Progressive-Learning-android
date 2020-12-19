package com.example.progressivelearning_android.model

import com.google.gson.annotations.SerializedName

data class LearningGoal(
    @SerializedName("goal") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("progress") var progress: Int = 0,
    @SerializedName("units") var units: ArrayList<Unit> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("user") var user: User? = null,
    @SerializedName("subject") var subject: Subject? = null,
    @SerializedName("subjectId") var subjectId: Int? = null,
    @SerializedName("userId") var userId: Int? = null
) {


    fun updateProgress() {
        val singleUnitPercentage = if (units.isNotEmpty()) 100 / units.size else  0
        var totalPercentage: Int = 0

        for(unit in units) {
            if(unit.completed) {
                totalPercentage =+ singleUnitPercentage
            }
        }
        progress = totalPercentage
    }


}