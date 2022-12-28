package com.unsplashed.client.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(
    @SerialName("impression_urls")
    val impressionUrls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    @SerialName("tagline_url")
    val taglineUrl: String
)