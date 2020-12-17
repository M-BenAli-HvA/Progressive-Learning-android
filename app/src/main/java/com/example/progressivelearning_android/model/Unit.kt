package com.example.progressivelearning_android.model

import com.google.gson.annotations.SerializedName

data class Unit(@SerializedName("name") var title: String,
                @SerializedName("completed") var completed: Boolean,
                @SerializedName("summary") var summary: String = "",
                @SerializedName("resources") var resources: ArrayList<Resource> = arrayListOf()) {

}