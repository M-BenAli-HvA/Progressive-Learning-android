package com.example.progressivelearning_android.model

enum class URLType {
    WIKIPEDIA, YOUTUBE, BOOK, SCIENTIFICPAPER, ARTICLE, IMAGE
}

data class Resource(var url: String, var type: URLType)