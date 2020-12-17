package com.example.progressivelearning_android.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("admin") val admin: Boolean = false) {

}