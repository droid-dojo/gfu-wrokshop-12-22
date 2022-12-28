package com.example.workshop.unsplash.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultPage(
    val total: Int,
    @SerialName("total_pages")
    val pages: Int,
    val results: List<Photo>,
)