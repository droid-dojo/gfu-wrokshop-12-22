package com.example.workshop.unsplash.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    @SerialName("small_s3")
    val smallS3: String,
    val thumb: String
)