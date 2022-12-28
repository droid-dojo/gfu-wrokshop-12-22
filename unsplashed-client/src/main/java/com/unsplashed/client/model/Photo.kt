package com.unsplashed.client.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("alt_description")
    val altDescription: String?,
    @SerialName("blur_hash")
    val blurHash: String,
    val color: String,
    @SerialName("created_at")
    val createdAt: String,
    val description: String?,
    val height: Int,
    val id: String,
    @SerialName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    @SerialName("promoted_at")
    val promotedAt: String?,
    val sponsorship: Sponsorship?,
    @SerialName("updated_at")
    val updatedAt: String,
    val urls: Urls,
    val user: User,
    val width: Int
)